package hciproject.datnh.englishquiz.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hciproject.datnh.englishquiz.R;
import hciproject.datnh.englishquiz.entity.WordQuizEntity;

public class CustomAdapter extends ArrayAdapter<WordQuizEntity>{
    private Context context;
    private int resource;
    private List<WordQuizEntity> word;

    public CustomAdapter(Context context, int resource, ArrayList<WordQuizEntity> word) {
        super(context, resource, word);
        this.context = context;
        this.resource = resource;
        this.word = word;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_word, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtWord = (TextView) convertView.findViewById(R.id.txtWord);
            viewHolder.txtMean = (TextView) convertView.findViewById(R.id.txtMean);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.txtType);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WordQuizEntity WordQuizEntity = word.get(position);
        viewHolder.txtWord.setText(WordQuizEntity.getName());
        viewHolder.txtMean.setText(WordQuizEntity.getMeaning());
        viewHolder.txtType.setText(WordQuizEntity.getType());
        return convertView;
    }

    public class ViewHolder {
        TextView txtWord, txtMean, txtType;

    }
}
