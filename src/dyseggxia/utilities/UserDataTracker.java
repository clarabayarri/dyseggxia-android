package dyseggxia.utilities;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import dyseggxia.domainModel.Problem;

public class UserDataTracker {

	public void sendTrackingData(Problem problem, boolean correct, String answer, int time, String userID, String securityCode) {
		JSONObject dadesAEnviar = new JSONObject();
		try {
			dadesAEnviar.put("levelNumber", problem.getLevel());
			dadesAEnviar.put("problemNumber", problem.getNumber());
			dadesAEnviar.put("word", problem.getDisplayedText());
			if(correct) {
				dadesAEnviar.put("correct", "S");
			}
			else dadesAEnviar.put("correct", "N");
			dadesAEnviar.put("answer", answer);
			dadesAEnviar.put("type", problem.getType());
			dadesAEnviar.put("time", time);
			dadesAEnviar.put("userID", userID);
			dadesAEnviar.put("securityCode", securityCode);
		} catch (JSONException e) {
			Log.e("log_tag", "error amb JSON a " + e.toString());
		}
		
		//enviar les dades
		String urlAmieggs = "http://www.dyseggxia.com/trackData.php";
		String response = WebAccessAdapter.getInstance().sendData(urlAmieggs, dadesAEnviar);
		System.out.println(response);
	}
}
