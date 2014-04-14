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
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

public class BebekTekCihaz extends Activity {

	ImageButton icon_rehber;
	Button btnBebekTekBaslat;
	EditText txtNumara;
	String str;
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
	    
		
		//TODO: ARAMA VEYA SMS Dï¿½NLEME SAYFASINDA CALISACAK 
		//      ï¿½NTENTE EXTRA KOYARAK SMS Mï¿½ ARAMA MI OLDUGUNU ANLAYACAZ ONA GORE ISLEM YAPILACAK 
		//		FALSE ï¿½SE CALLING, TRUE ï¿½SE SMS
		btnBebekTekBaslat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int selectedId = radioGrup.getCheckedRadioButtonId();
				Intent intent = new Intent(BebekTekCihaz.this,BabyListen.class); //diger sayfaya gidebilmek icin
				
				if(selectedId==R.id.radioArama)
				{
					
					intent.putExtra("number", txtNumara.getText().toString()); 
					//Toast.makeText(getApplicationContext(), "Arama seçildi numara:"+txtNumara.getText(), Toast.LENGTH_SHORT).show();
				}
				else if(selectedId==R.id.radioSMS)
				{

					intent.putExtra("message", getResources().getString(R.string.sms_body));
					intent.putExtra("number", txtNumara.getText().toString());

					//Toast.makeText(getApplicationContext(), "SMS seçildi numara:"+txtNumara.getText(), Toast.LENGTH_SHORT).show();
						
				}
				str=txtNumara.getText().toString();
				if(!str.matches("")){
					if(selectedId==R.id.radioSMS){
						Toast.makeText(getApplicationContext(), "SMS seçildi numara:"+txtNumara.getText(), Toast.LENGTH_SHORT).show();
					}
					else if(selectedId==R.id.radioArama){
						Toast.makeText(getApplicationContext(), "Arama seçildi numara:"+txtNumara.getText(), Toast.LENGTH_SHORT).show();
					}
				
				startActivity(intent);
				}
				else{
					Toast.makeText(getApplicationContext(), "XXXXXXXXX", Toast.LENGTH_LONG).show();
				return;
			}
			}
		});
		
	

	
	}
	

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) 
	{
		super.onActivityResult(reqCode, resultCode, data);
		
		if ((reqCode == PICK_CONTACT) && (resultCode == RESULT_OK))
		{
			Uri contact = data.getData();
			ContentResolver cr = getContentResolver();
			@SuppressWarnings("deprecation")
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
	
	
	
	

