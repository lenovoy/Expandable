package com.vivo.expandable;

import java.util.ArrayList;
import java.util.List;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ExpandableListActivity {

	List<String> group;
	List<List<String>> child;
	ContactsInfoAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		initializeData();
		getExpandableListView().setAdapter(new ContactsInfoAdapter());
		getExpandableListView().setCacheColorHint(0);
	}
	
	private void initializeData(){
		group=new ArrayList<String>();
		child=new ArrayList<List<String>>();
		group.add("Fairy");
		group.add("Jerry");
		group.add("Tom");
		group.add("Bill");
		addInfo("Andy",new String[]{"male","138123***","GuangZhou"}); 
		for(int i=0;i<4;i++)
			child.add(new ArrayList<String>());
//        addInfo("Fairy",new String[]{"female","138123***","GuangZhou"});  
//        addInfo("Jerry",new String[]{"male","138123***","ShenZhen"});  
//        addInfo("Tom",new String[]{"female","138123***","ShangHai"});  
//        addInfo("Bill",new String[]{"male","138231***","ZhanJiang"});  

	}
	
	private void addInfo(String g,String[] c){
		group.add(g);
		List<String> childItem=new ArrayList<String>();
		for(int i=0;i<c.length;i++)
			childItem.add(c[i]);
		child.add(childItem);
	}
	
	class ContactsInfoAdapter extends BaseExpandableListAdapter{

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return group.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return child.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return group.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return child.get(groupPosition).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			String string=group.get(groupPosition);
			TextView txtView=getGenericView(string);
			
			return txtView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			String string=child.get(groupPosition).get(childPosition);
			TextView txtView=getGenericView(string);
			txtView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplication(), "click the child",Toast.LENGTH_LONG).show();
				}
			});
			return txtView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void onGroupCollapsed(int groupPosition) {
			if(0!=child.get(groupPosition).size()){
				child.get(groupPosition).clear();
			}
	    }
		
		@Override
	    public void onGroupExpanded(int groupPosition) {
			String[] strs=new String[]{"female","138123***","GuangZhou"};
			for(int i=0;i<strs.length;i++){
				child.get(groupPosition).add(strs[i]);
			}
	    }
		
		
		public TextView getGenericView(String s){
			AbsListView.LayoutParams lp=new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,100);
			TextView text=new TextView(MainActivity.this);
			text.setLayoutParams(lp);
			text.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
			text.setPadding(100, 0, 0, 0);
			text.setText(s);
			return text;
		}
		
	}
}
