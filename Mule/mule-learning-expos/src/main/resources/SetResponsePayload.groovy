package groovy
import groovy.xml.MarkupBuilder

def writer = new StringWriter()
def out = new MarkupBuilder(writer)


/*System.out.println(" Response Payload" +message.getInvocationProperty('responsePayload'))*/

System.out.println("  Payload" +payload.toString())


out."soapenv:Envelope"("xmlns:soapenv": "http://schemas.xmlsoap.org/soap/envelope/","xmlns:v1":"http://www.genesisenergy.co.nz/esb/financial/getAccountEvent/v1"){
	"soapenv:Header"{}
	"soapenv:Body"{
		"v1:getAccountEventResponse" {
			"responseStatus"()
			"getAccountEventResponseResult"{
				payload.each{dbRow->
				   "accountEvent"{
					    "id"(dbRow['id'])
						"date"(dbRow['thisdate'])
						"amount"(dbRow['amount'])
						"code"(dbRow['code'])
						"description"(dbRow['description'])
						"netAmount"(dbRow['netAmount'])
						"gst"(dbRow['gst'])
						"geoCode"(dbRow['geoCode'])
						"type"(dbRow['type'])
						"percentage"(dbRow['percentage'])
						"feeIncluded"(dbRow['feeIncluded'])					
						"invoiceId"(dbRow['invoiceId'])
						"invoiceUtilityType"(dbRow['invoiceUtilityType'])
						"invoiceDueDate"(dbRow['invoiceDueDate'])
						"invoiceDescription"(dbRow['invoiceDescription'])
						"property"(dbRow['property'])
						"thirdPartyBillingItem"(dbRow['thirdPartyBillingItem'])
						"bill"{
							"statementId"(dbRow['statementId'])
							"date"(dbRow['billdate'])
							"dueDate"(dbRow['dueDate'])
							"amount"(dbRow['amount'])
						}
						"createdBy"(dbRow['createdBy'])
					   	
					}				
				}
			}
		}
	}
}


return writer.toString()