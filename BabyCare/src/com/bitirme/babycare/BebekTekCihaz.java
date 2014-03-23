package com.bitirme.babycare;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;//TODO se�ilen contactin numaras�n� almak i�in bu laz�mm�� ama halledemedim
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
//TODO aray�zdeki elementleri burda tan�tt�m ayr� fonksiyon i�inde		
		icon_rehber = (ImageButton)findViewById(R.id.icon_rehber);
		btnBebekTekBaslat=(Button)findViewById(R.id.btnBebekTekBaslat);
		txtNumara = (EditText) findViewById(R.id.txtNumara);
	    icon_rehber.setOnClickListener(new OnClickListener() {
//TODO rehber iconunun onclicki
			
			@Override
			public void onClick(View v) {
//TODO burda contact listi ��kart�yorum ama t�klad���mda textbox'a numara de�il adres 
				//d���yor. bunu halletmemiz laz�m.
				Intent intent= new Intent(Intent.ACTION_PICK,  Contacts.CONTENT_URI);

		        startActivityForResult(intent, PICK_CONTACT);
				
			}
		});
		
		btnBebekTekBaslat.setOnClickListener(new OnClickListener() {
//TODO Bebek telsizi ba�lat�n onclicki
			@Override
			public void onClick(View arg0) {
				
				/*Intent intentArama = new Intent(Intent.ACTION_CALL);
				intentArama.setData(Uri.parse("tel"+txtNumara.getText().toString()));
				startActivity(intentArama);*/
				Toast.makeText(getApplicationContext(), "Numara aran�yor...", Toast.LENGTH_LONG).show();
				//if(radio_Arama.isChecked()){
				/*try {
					Intent intentArama = new Intent(Intent.ACTION_CALL);
					intentArama.setData(Uri.parse("tel"+txtNumara.getText().toString()));
					startActivity(intentArama);
					}
					catch(Exception e) {
                        Toast.makeText(getApplicationContext(), "Problem calling number.", Toast.LENGTH_LONG).show();
                    }
					
					Toast.makeText(getApplicationContext(), "Numara aran�yor...", Toast.LENGTH_LONG).show();
					
				}*/
					//else if(radio_SMS.isChecked()){
					/*String message = "Hello world.";
					Intent intentSMS = new Intent(Intent.ACTION_VIEW);
					intentSMS.setData(Uri.parse("sms:"
	                        + txtNumara.getText().toString()));
					intentSMS.putExtra( "sms_body", message )*/
					/*Toast.makeText(getApplicationContext(), "Numaraya sms g�nderildi...", Toast.LENGTH_LONG).show();
//TODO COmment
					onClick'te ya da Listener'da bi s�k�nt� var. Radiobutton kontrol�n� d��arda
					yapmam�z gerekiyo olabilir. Arama ve smsi burda sadece denemek i�in koydum, ba�ka
					activity ye ge�ip yap�caz zaten.*/
				//}
				
				
			}
		});
		
	}
	
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
	  super.onActivityResult(reqCode, resultCode, data);

	  if ((reqCode == PICK_CONTACT) && (resultCode == RESULT_OK)) {
		  txtNumara.setText(data.getDataString());
		  //TODO san�r�m contact i se�tikten sonra textboxa gelen garip yaz�n�n sebebi bu getDataString
		  //belirsiz bi �ey al�yo. numaray� almak i�in ba�ka bi �ey laz�m.
		  }
	}
	
	//TODO genel olarak yapt���m �eyler: 
	/*
	 * BebekTekCihaz ve BebekCiftCihaz class lar� eklendi
	 * Arama ve sms i�in gereken permissionlar eklendi
	 * rehber icon u eklendi rehbere giri� sa�land�
	 * 
	 * */
	
	
	
	
	
	
	
}
