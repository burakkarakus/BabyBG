package com.bitirme.babycare;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

public class BebekTekCihaz extends Activity {

	ImageButton icon_rehber;
	Button btnBebekTekBaslat;
	EditText txtNumara;
	
	RadioGroup radioGrup;
	private static int PICK_CONTACT = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bebek_tek);
		
		radioGrup = (RadioGroup) findViewById(R.id.radioUyariTuruGrup);
		icon_rehber = (ImageButton)findViewById(R.id.icon_rehber);
		btnBebekTekBaslat=(Button)findViewById(R.id.btnBebekTekBaslat);
		txtNumara = (EditText) findViewById(R.id.txtNumara);
		
		
	    icon_rehber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				Intent intent= new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT);
				
			}
		});
	    
		
		//TODO: ARAMA VEYA SMS D�NLEME SAYFASINDA CALISACAK 
		//      �NTENTE EXTRA KOYARAK SMS M� ARAMA MI OLDUGUNU ANLAYACAZ ONA GORE ISLEM YAPILACAK 
		//		FALSE �SE CALLING, TRUE �SE SMS
		btnBebekTekBaslat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int selectedId = radioGrup.getCheckedRadioButtonId();
				Intent intent = new Intent(BebekTekCihaz.this,BabyListen.class); //diger sayfaya gidebilmek icin
				
				if(selectedId==R.id.radioArama)
				{
					Uri uri= Uri.parse("tel:"+txtNumara);
					Intent callIntent= new Intent(Intent.ACTION_CALL,uri); // diger sayfada kullanilacak intent 
					
					intent.putExtras(callIntent); // birisinin digerinin icine gomuyorum diger sayfanin sms mi call mi oldugunu bilmesine gerek yok
												// sadece startactivty callIntent diyecek
					Toast.makeText(getApplicationContext(), "Arama se�ildi numara:"+txtNumara.getText(), Toast.LENGTH_LONG).show();
				}
				else if(selectedId==R.id.radioSMS)
				{
					Uri uri=  Uri.parse("sms:"+txtNumara); 
					Intent smsIntent= new Intent(Intent.ACTION_SENDTO,uri);
					smsIntent.putExtra("sms_body", getResources().getString(R.string.sms_body));//values/strings teki degeri donduruyor
					intent.putExtras(smsIntent);
					Toast.makeText(getApplicationContext(), "SMS se�ildi numara:"+txtNumara.getText(), Toast.LENGTH_LONG).show();
						
				}
				startActivity(intent);
				
			}
		});
		
	
		//TODO: Arama veya mesaj yollama bu sayfada Ger�ekle�meyecek
		
		
//		btnBebekTekBaslat.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				
//				Intent intentArama = new Intent(Intent.ACTION_CALL);
//				intentArama.setData(Uri.parse("tel"+txtNumara.getText().toString()));
//				startActivity(intentArama);
//				Toast.makeText(getApplicationContext(), "Numara aran�yor...", Toast.LENGTH_LONG).show();
//				if(radio_Arama.isChecked()){
//				try {
//					 intentArama = new Intent(Intent.ACTION_CALL);
//					intentArama.setData(Uri.parse("tel"+txtNumara.getText().toString()));
//					startActivity(intentArama);
//					}
//					catch(Exception e) {
//                        Toast.makeText(getApplicationContext(), "Problem calling number.", Toast.LENGTH_LONG).show();
//                    }
//					
//					Toast.makeText(getApplicationContext(), "Numara aran�yor...", Toast.LENGTH_LONG).show();
//					
//				}
//					else if(radio_SMS.isChecked()){
//					String message = "Hello world.";
//					Intent intentSMS = new Intent(Intent.ACTION_VIEW);
//					intentSMS.setData(Uri.parse("sms:"
//	                        + txtNumara.getText().toString()));
//					intentSMS.putExtra( "sms_body", message );
//					Toast.makeText(getApplicationContext(), "Numaraya sms g�nderildi...", Toast.LENGTH_LONG).show();
//////TODO COmment
////					onClick'te ya da Listener'da bi s�k�nt� var. Radiobutton kontrol�n� d��arda
////					yapmam�z gerekiyo olabilir. Arama ve smsi burda sadece denemek i�in koydum, ba�ka
////					activity ye ge�ip yap�caz zaten.*/
//				}
//				
//				
//			}
//		});
	
	}
	

	@Override
	
	public void onActivityResult(int reqCode, int resultCode, Intent data) 
	{
		super.onActivityResult(reqCode, resultCode, data);
		
		if ((reqCode == PICK_CONTACT) && (resultCode == RESULT_OK))
		{
			Uri contact = data.getData();
			ContentResolver cr = getContentResolver();
			Cursor c = managedQuery(contact, null, null, null, null);
			while(c.moveToNext())
			{
				String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
				if (Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
				{
					Cursor pCur = cr.query(Phone.CONTENT_URI,null,Phone.CONTACT_ID +" = ?", new String[]{id}, null);
					String phone=null;
						while(pCur.moveToNext())
						{
							phone = pCur.getString(pCur.getColumnIndex(Phone.NUMBER));
						}
						txtNumara.setText(phone);
				}
			}
		}
	}
}
	
	
	
	

