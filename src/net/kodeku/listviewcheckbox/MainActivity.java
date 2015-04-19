package net.kodeku.listviewcheckbox;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	MyCustomAdapter dataAdapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button myButton = (Button) findViewById(R.id.button1);
		myButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				StringBuffer responseText = new StringBuffer();
				responseText.append("Pemrograman yang ingin anda pelajari adalah");

				ArrayList<MenuPilihan> pilihanList = dataAdapter.pilihanList;
				for (int i = 0; i < pilihanList.size(); i++) {
					MenuPilihan pilihan = pilihanList.get(i);
					if (pilihan.isSelected()) {
						responseText.append("\n" + pilihan.getName());
					}
				}

				Toast.makeText(getApplicationContext(), responseText,
						Toast.LENGTH_SHORT).show();

			}
		});

		displayListView();

	}

	private void displayListView() {

		ArrayList<MenuPilihan> pilihanList = new ArrayList<MenuPilihan>();
		MenuPilihan pilihan = new MenuPilihan("1", "Java", true);
		pilihanList.add(pilihan);
		pilihan = new MenuPilihan("2", "C++", false);
		pilihanList.add(pilihan);
		pilihan = new MenuPilihan("3", "Java Script", false);
		pilihanList.add(pilihan);
		pilihan = new MenuPilihan("4", "PHP", false);
		pilihanList.add(pilihan);
		pilihan = new MenuPilihan("5", "Phyton", true);
		pilihanList.add(pilihan);
		pilihan = new MenuPilihan("6", "Perl", false);
		pilihanList.add(pilihan);
		pilihan = new MenuPilihan("7", "Pascal", false);
		pilihanList.add(pilihan);

		// Buata array adapter dari data pilihanList
		dataAdapter = new MyCustomAdapter(this, R.layout.activity_listview_adapter,
				pilihanList);
		ListView listView = (ListView) findViewById(R.id.listView1);
		// Assign adapter to ListView
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				MenuPilihan pilihan = (MenuPilihan) parent.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(),
						"Clicked on Row: " + pilihan.getName(),
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	private class MyCustomAdapter extends ArrayAdapter<MenuPilihan> {

		private ArrayList<MenuPilihan> pilihanList;

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<MenuPilihan> pilihanList) {
			super(context, textViewResourceId, pilihanList);
			this.pilihanList = new ArrayList<MenuPilihan>();
			this.pilihanList.addAll(pilihanList);
		}

		private class ViewHolder {
			TextView id;
			CheckBox name;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.activity_listview_adapter, null);

				holder = new ViewHolder();
				holder.name = (CheckBox) convertView
						.findViewById(R.id.checkBox1);
				convertView.setTag(holder);

				holder.name.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox cb = (CheckBox) v;
						MenuPilihan pilihan = (MenuPilihan) cb.getTag();
						Toast.makeText(
								getApplicationContext(),
								"Clicked on Checkbox: " + cb.getText() + " is "
										+ cb.isChecked(), Toast.LENGTH_SHORT)
								.show();
						pilihan.setSelected(cb.isChecked());
					}
				});
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			MenuPilihan pilihan = pilihanList.get(position);
			holder.name.setText(pilihan.getName());
			holder.name.setChecked(pilihan.isSelected());
			holder.name.setTag(pilihan);

			return convertView;

		}

	}

}
