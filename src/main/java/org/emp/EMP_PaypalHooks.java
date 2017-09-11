package org.emp;


import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.EventType;
import com.paypal.api.payments.Webhook;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class EMP_PaypalHooks {
	
	private String[] eventTypeStrings = {
		// Billing plan and subscriptions	
		"BILLING.PLAN.CREATED",
		"BILLING.PLAN.UPDATED",
		"BILLING.SUBSCRIPTION.CANCELLED",
		"BILLING.SUBSCRIPTION.CREATED",
		"BILLING.SUBSCRIPTION.RE-ACTIVATED",
		"BILLING.SUBSCRIPTION.SUSPENDED",
		"BILLING.SUBSCRIPTION.UPDATED",
		
		// Customer disputes
		"CUSTOMER.DISPUTE.CREATED",
		"CUSTOMER.DISPUTE.RESOLVED",
		"CUSTOMER.DISPUTE.UPDATED",
		
		// Authorizations
		"IDENTITY.AUTHORIZATION-CONSENT.REVOKED",
		
		// Invoices
		"INVOICING.INVOICE.CANCELLED",
		"INVOICING.INVOICE.PAID",
		"INVOICING.INVOICE.REFUNDED",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
	};
	
	public EMP_PaypalHooks(APIContext apiContext) {
		setupPaypalHooks(apiContext);
	}
	
	private void setupPaypalHooks(APIContext apiContext) {
		List<EventType> eventTypes = new ArrayList<EventType>(); 
		   
		EventType eventType1 = new EventType(); 
		eventType1.setName("VAULT.CREDIT-CARD.CREATED"); 

		EventType eventType2 = new EventType(); 
		eventType2.setName("VAULT.CREDIT-CARD.UPDATED"); 

		eventTypes.add(eventType1); 
		eventTypes.add(eventType2);

		Webhook webhook = new Webhook();
		webhook.setUrl("https://f6153ef32.ngrok.io");
		webhook.setEventTypes(eventTypes);
		
		try {
			  Webhook createdWebhook = webhook.create(apiContext, webhook);
			  System.out.println("Webhook successfully created with ID " + createdWebhook.getId());
		}
		catch (PayPalRESTException e) {
			  System.err.println(e.getDetails());
		}		
	}
}
