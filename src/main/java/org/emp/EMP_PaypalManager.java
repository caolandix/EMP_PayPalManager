package org.emp;


import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.SDKVersion;

/**
 * Hello world!
 *
 */
@SuppressWarnings("unused")
public class EMP_PaypalManager {
    public static void main( String[] args ) {

		// Replace with your application client ID and secret
		String clientId = "ASTD9rQ0ys66YeLHtQtIhBFm42ADflAYpL4fuX3k2Os8yb-e89pPskc3VQ5BFJ189TcWk4it1DhoX084";
		String clientSecret = "EEbFxCNHzKaCoYlKI5XWyDO_L8SeS6qdOw2Koldxy-Y_PHGxJOVB-kS1Oqo2TxQ9PTuP96NGYRcDAAIN";

		APIContext context = new APIContext(clientId, clientSecret, "sandbox");
		
		SDKVersion version = context.getSdkVersion();
		boolean bVal = true;
    }
}
