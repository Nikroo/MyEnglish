//package by.gsu.curiosity.mybd.lists;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.graphics.drawable.Drawable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//import by.gsu.curiosity.mybd.R;
//
///**
// * Created by Curiosity on 17.11.2015.
// */
//
//// Адаптер данных
//
//public class CustomAdapter extends BaseAdapter {
//    private LayoutInflater inflater;
//    private ArrayList<ContactItem> data;
//    private Context context;
//
//    public CustomAdapter(Context context, ArrayList<ContactItem> data) {
//        inflater = (LayoutInflater)context.getSystemService(
//                Context.LAYOUT_INFLATER_SERVICE);
//        this.context = context;
//        this.data = data;
//    }
//
//    @Override
//    public int getCount() {
//        return data.size();
//    }
//
//    @Override
//    public ContactItem getItem(int position) {
//        return data.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = convertView;
//        if (convertView == null) {
//            view = inflater.inflate(R.layout.row, null);
//        }
//        final ContactItem item = data.get(position);
//
//        ImageView image = (ImageView)view.findViewById(R.id.image);
//        TextView name = (TextView)view.findViewById(R.id.name);
//
//// Метод setImageDrawable представляет в коде наш загружаемый файл с изображением
//// getPhotoPath переменная хранит полный путь до файла с изображением
//        image.setImageDrawable(Drawable.createFromPath(item.getPhotoPath()));
//        name.setText(item.getName());
//
//        ImageView edit = (ImageView)view.findViewById(R.id.edit);
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openEditDialog(item);
//            }
//        });
//
//        return view;
//    }
//
//    private void openEditDialog(final ContactItem item) {
//        LayoutInflater dlgInflater = (LayoutInflater)context.getSystemService(
//                Context.LAYOUT_INFLATER_SERVICE);
//        View root = dlgInflater.inflate(R.layout.edit, null);
//
//        final EditText name = (EditText)root.findViewById(R.id.detailsName);
//        final EditText phone = (EditText)root.findViewById(R.id.detailsPhone);
//        final EditText about = (EditText)root.findViewById(R.id.detailsAbout);
//
//        name.setText(item.getName());
//        phone.setText(item.getPhone());
//        about.setText(item.getAbout());
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setView(root);
//        builder.setMessage("Edit contact");
//
//        builder.setPositiveButton("Save",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        item.setName(name.getText().toString());
//                        item.setPhone(phone.getText().toString());
//                        item.setAbout(about.getText().toString());
//                    }
//                });
//
//        builder.setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//
//        builder.setCancelable(false);
//        builder.create();
//        builder.show();
//    }
//}
