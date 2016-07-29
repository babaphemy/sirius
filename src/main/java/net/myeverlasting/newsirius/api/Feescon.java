package net.myeverlasting.newsirius.api;

import java.net.URI;
import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.myeverlasting.newsirius.entity.Refi;
import net.myeverlasting.newsirius.entity.Transaction;
import net.myeverlasting.newsirius.service.TranxServ;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping(value="/pay")
public class Feescon {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	@Autowired
	private TranxServ tranxServ;
	
	public static final String productid = "6205";
	public static final String pitemid = "101";
	public static final String rurl = "http://localhost:5090/sirius/pay/quer";
	public static final String mac = "D3D1D05AFE42AD50818167EAC73C109168A0F108F32645C8B59E897FA930DA44F9230910DAC9E20641823799A107A02068F7BC0F4CC41D2952E249552255710F";
	public static final String iswrest_test = "https://stageserv.interswitchng.com/test_paydirect/api/v1/gettransaction.json";
	public static final String iswrest_live = "https://webpay.interswitchng.com/paydirect/api/v1/gettransaction.json";
	
	@RequestMapping(value="/quer", produces="application/json")
	public ModelAndView callback(@RequestParam("txnref") String dref) throws Exception{
		String page;
		Transaction tt = tranxServ.byTref(dref);
		int amt = tt.getAmount();
		int kobo = amt * 100;
		ResponseEntity<?> dres = queryIsw(dref,kobo, mac, productid, iswrest_test);
		JSONObject jo = JSONObject.fromObject(dres.getBody());
		String res = (String) jo.get("ResponseCode");
		if(res.equals("00")) {
			String payref = (String)jo.get("PaymentReference");
			String resdesc = (String)jo.get("ResponseDescription");	
			int amti = jo.getInt("Amount");
			tt.setAmount(amti);
			tt.setResponseCode(res);
			tt.setPaymentRef(payref);
			tt.setPaymentstatus("COMPLETED");
			tt.setResponseDescription(resdesc);
			tranxServ.addTranx(tt);
//			sendMail(res,resdesc, tt.getEmail(),hisname,payref);
			page="success.jsp";
		} else {
			tt.setPaymentstatus("FAILED"); // dont give value
			tranxServ.addTranx(tt);
			page = "failed.jsp";
		}
		ModelAndView mod = new ModelAndView(page);
		mod.addObject("callback", dres);
		mod.addObject("ref", dref);
		
		return mod;
		
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json", value="/dopayment", consumes = {"application/x-www-form-urlencoded"})
	public ModelAndView confirm(HttpServletRequest req, Transaction tr ) throws Exception{
		int nairaamt = tr.getAmount();
		int kobo = nairaamt * 100;
		String koboamount = String.valueOf(kobo);
		tr.setResponseDescription("PENDING");
		//Date dt = new Date();
		UUID uu = UUID.randomUUID();
		String uui = uu.toString().replaceAll("-", "");
		tr.setTxnref(uui);
		tranxServ.addTranx(tr);
		
		// save tranx
		String truehash = pasherpay(tr.getTxnref(),productid,pitemid,koboamount,rurl,mac);
        req.getSession().setAttribute("dhash", truehash);
        req.getSession().setAttribute("pdtid", productid);
        req.getSession().setAttribute("rurl", rurl);       
        ModelAndView mod = new ModelAndView("confirm.jsp");
        mod.addObject("tranx", tr);
        
		
		return mod;
		
	}
	
	@ResponseBody
	private ResponseEntity<?> queryIsw(String ref, int amt, String mackey, String pdt, String ep) throws Exception{
		//String pdtid = Options.productid;
		//String salt = Options.mac;
		RestTemplate restt = new RestTemplate();
		//String uri = Options.iswrest_test;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("productid", pdt);
		params.add("transactionreference", ref);
		params.add("amount", String.valueOf(amt));
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(ep).queryParams(params).build();
		URI oro = uriComponents.toUri();
		HttpHeaders hea = new HttpHeaders();
		String hs = pasher(pdt, ref, mackey);
		hea.add("hash", hs);
		HttpEntity<String> hent = new HttpEntity<String>(hea);
		
		ResponseEntity<Object> resp =  restt.exchange(oro, HttpMethod.GET, hent, Object.class);		
		System.out.println(resp + "is the isw response");
		return resp;
		
	}
	
	private static String pasherpay(String ref,String pdtid,String pitem, String amt,String rurl,String mac) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        String tohash = ref + pdtid + pitem + amt + rurl + mac;
        String dohash = null;
        md.update(tohash.getBytes());
        byte[] bt = md.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<bt.length; i++){
        	sb.append(Integer.toString((bt[i] & 0xff) + 0x100, 16).substring(1));
        }
        dohash = sb.toString();
        return dohash;
    }
	
	private static String pasher(String pdtid, String ref, String salt) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		String tohash = pdtid + ref + salt;	
		String fhash = null;
		md.update(tohash.getBytes());
		byte[] bt = md.digest();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<bt.length; i++){
			sb.append(Integer.toString((bt[i] & 0xff) + 0x100, 16).substring(1));
		}
		fhash = sb.toString();
		return fhash;
		
		
	}

	
	
	
		

}
