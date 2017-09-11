package org.emp;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.SDKVersion;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Capture;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Order;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;


/**
 * Hello world!
 *
 */
@SuppressWarnings("unused")
public class EMP_PaypalManager {
	
	private String m_clientId = "ASTD9rQ0ys66YeLHtQtIhBFm42ADflAYpL4fuX3k2Os8yb-e89pPskc3VQ5BFJ189TcWk4it1DhoX084";
	private String m_clientSecret = "EEbFxCNHzKaCoYlKI5XWyDO_L8SeS6qdOw2Koldxy-Y_PHGxJOVB-kS1Oqo2TxQ9PTuP96NGYRcDAAIN";
	
    public static void main( String[] args ) {
    	CreateAndProcessOrder();
    }
    
    private static void CreateAndProcessOrder() {
		// Replace with your application client ID and secret
		String clientId = "ASTD9rQ0ys66YeLHtQtIhBFm42ADflAYpL4fuX3k2Os8yb-e89pPskc3VQ5BFJ189TcWk4it1DhoX084";
		String clientSecret = "EEbFxCNHzKaCoYlKI5XWyDO_L8SeS6qdOw2Koldxy-Y_PHGxJOVB-kS1Oqo2TxQ9PTuP96NGYRcDAAIN";
		APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
		
    	// Add payer details
    	Payer payer = new Payer();
    	payer.setPaymentMethod("paypal");

    	// Add redirect URLs
    	RedirectUrls redirectUrls = new RedirectUrls();
    	redirectUrls.setReturnUrl("http://localhost:3000/process");
    	redirectUrls.setCancelUrl("http://localhost:3000/cancel");

    	// Set payment details
    	Details details = new Details();
    	details.setShipping("1");
    	details.setSubtotal("5");
    	details.setTax("1");

    	// Payment amount
    	Amount amount = new Amount();
    	amount.setCurrency("USD");

    	// Total must be equal to sum of shipping, tax and subtotal.
    	String total = details.getSubtotal();
    	amount.setTotal("7");
    	amount.setDetails(details);

    	// Transaction information
    	Transaction transaction = new Transaction();
    	transaction.setAmount(amount);
    	transaction.setDescription("This is the payment description.");

    	// Add transaction to a list
    	List<Transaction> transactions = new ArrayList<Transaction>();
    	transactions.add(transaction);

    	// Add payment details
    	Payment payment = new Payment();
    	payment.setIntent("order");
    	payment.setPayer(payer);
    	payment.setRedirectUrls(redirectUrls);
    	payment.setTransactions(transactions);
    	
    	// Create payment
    	try {
    		Payment createdPayment = payment.create(apiContext);

    		Iterator links = createdPayment.getLinks().iterator();
    		while (links.hasNext()) {
    			Links link = (Links) links.next();
    			if (link.getRel().equalsIgnoreCase("approval_url")) {
    				//REDIRECT USER TO link.getHref()
    			}
    		}
    	}
    	catch (PayPalRESTException e) {
    		System.err.println(e.getDetails());
    	}
    	
    	boolean bVal = true;
    }
}
