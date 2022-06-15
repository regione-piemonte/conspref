/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import it.csi.conspref.consprefnotifica.util.NotificatoreUtil;

public class TestNotificatore {
	private static final String SUBJ_TITLE="Notifica avvenuta variazione consenso";
	private static final String MSG_BODY_X_DELEGANTE="Ciao @DELEGANTE@, il gg/mm/aaaa il tuo @delegato ha richiesto di variare il consenso  @descConsenso@ per tuo conto. Riceverai conferma del corretto invio presso le Aziende interessate.";
	private static final String MSG_BODY_X_CITTADINO="Ciao @CITTADINO@, il @DTVARIAZIONE@ hai variato il tuo consenso per @DESCCONSENSO@. ";
	private static final String NOTIIFCATORE_ACTION_DESTINATION="https://xyz.csi.it/temp/pwa";
	private static final String APPLICATION_URL=NOTIIFCATORE_ACTION_DESTINATION+"/la-mia-salute/#/conspref";
	private static final String NOTIFICATORE_TOKEN="xyz";
	private static final String URL_ENDPOINT="https://xyz.csi.it/xyz/api/v1/topics/messages";
	private static final SimpleDateFormat dtFormatter = new SimpleDateFormat("dd/MM/yyyy");
	
	private static final String destNotificaCF="xyz";

    public static void main(String[] args) 
    { 

      Map<String,String> replacements = new HashMap<>();
      		replacements.put("@DELEGANTE@", "xyz xyz");
      		replacements.put("@CITTADINO@","CF");
      		replacements.put("@DESCCONSENSO@","xyz");
      		replacements.put("@DTVARIAZIONE@", dtFormatter.format(new Date()));
  
        new NotificatoreUtil().callNotificatore_test(destNotificaCF, SUBJ_TITLE, MSG_BODY_X_CITTADINO, APPLICATION_URL, URL_ENDPOINT, NOTIFICATORE_TOKEN, replacements);
    } 
}
