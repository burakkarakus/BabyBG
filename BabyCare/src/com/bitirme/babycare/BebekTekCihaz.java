package com.bitirme.babycare;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;//TODO seçilen contactin numarasýný almak için bu lazýmmýþ ama halledemedim
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

public class BebekTekCihaz extends Activity {

	ImageButton icon_rehber;
	Button btnBebekTekBaslat;
	EditText txtNumara;
	RadioButton radio_SMS;
	RadioButton radio_Arama;
	private static int PICK_CONTACT = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bebek_tek);
		addListenerOnButton();
	
	}
	
	public void addListenerOnButton(){
//TODO arayüzdeki elementleri burda tanýttým ayrý fonksiyon içinde		
		icon_rehber = (ImageButton)findViewById(R.id.icon_rehber);
		btnBebekTekBaslat=(Button)findViewById(R.id.btnBebekTekBaslat);
		txtNumara = (EditText) findViewById(R.id.txtNumara);
	    icon_rehber.setOnClickListener(new OnClickListener() {
//TODO rehber iconunun onclicki
			
			@Override
			public void onClick(View v) {
//TODO burda contact listi çýkartýyorum ama týkladýðýmda textbox'a numara deðil adres 
				//düþüyor. bunu halletmemiz lazým.
				Intent intent= new Intent(Intent.ACTION_PICK,  Contacts.CONTENT_URI);

		        startActivityForResult(intent, PICK_CONTACT);
				
			}
		});
		
		btnBebekTekBaslat.setOnClickListener(new OnClickListener() {
//TODO Bebek telsizi baþlatýn onclicki
			@Override
			public void onClick(View arg0) {
				
				/*Intent intentArama = new Intent(Intent.ACTION_CALL);
				intentArama.setData(Uri.parse("tel"+txtNumara.getText().toString()));
				startActivity(intentArama);*/
				Toast.makeText(getApplicationContext(), "Numara aranýyor...", Toast.LENGTH_LONG).show();
				//if(radio_Arama.isChecked()){
				/*try {
					Intent intentArama = new Intent(Intent.ACTION_CALL);
					intentArama.setData(Uri.parse("tel"+txtNumara.getText().toString()));
					startActivity(intentArama);
					}
					catch(Exception e) {
                        Toast.makeText(getApplicationContext(), "Problem calling number.", Toast.LENGTH_LONG).show();
                    }
					
					Toast.makeText(getApplicationContext(), "Numara aranýyor...", Toast.LENGTH_LONG).show();
					
				}*/
					//else if(radio_SMS.isChecked()){
					/*String message = "Hello world.";
					Intent intentSMS = new Intent(Intent.ACTION_VIEW);
					intentSMS.setData(Uri.parse("sms:"
	                        + txtNumara.getText().toString()));
					intentSMS.putExtra( "sms_body", message )*/
					/*Toast.makeText(getApplicationContext(), "Numaraya sms gönderildi...", Toast.LENGTH_LONG).show();
//TODO COmment
					onClick'te ya da Listener'da bi sýkýntý var. Radiobutton kontrolünü dýþarda
					yapmamýz gerekiyo olabilir. Arama ve smsi burda sadece denemek için koydum, baþka
					activity ye geçip yapýcaz zaten.*/
				//}
				
				
			}
		});
		
	}
	
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
	  super.onActivityResult(reqCode, resultCode, data);

	  if ((reqCode == PICK_CONTACT) && (resultCode == RESULT_OK)) {
		  txtNumara.setText(data.getDataString());
		  //TODO sanýrým contact i seçtikten sonra textboxa gelen garip yazýnýn sebebi bu getDataString
		  //belirsiz bi þey alýyo. numarayý almak için baþka bi þey lazým.
		  }
	}
	
	//TODO genel olarak yaptýðým þeyler: 
	/*
	 * BebekTekCihaz ve BebekCiftCihaz class larý eklendi
	 * Arama ve sms için gereken permissionlar eklendi
	 * rehber icon u eklendi rehbere giriþ saðlandý
	 * 
	 * */
	
	
	
	
	
	
	
}
