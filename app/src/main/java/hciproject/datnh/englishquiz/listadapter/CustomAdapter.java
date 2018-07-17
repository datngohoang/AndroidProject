package hciproject.datnh.englishquiz.listadapter;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hciproject.datnh.englishquiz.R;
import hciproject.datnh.englishquiz.entity.WordQuizEntity;

public class CustomAdapter extends ArrayAdapter<WordQuizEntity> implements Filterable {
    private Context context;

    private int resource;
    private List<WordQuizEntity> word;
    private List<WordQuizEntity> allWord;
    private List<WordQuizEntity> filterWord;
    ValueFilter valueFilter;

    public CustomAdapter(Context context, int resource, ArrayList<WordQuizEntity> word) {
        super(context, resource, word);
        this.context = context;
        this.resource = resource;
        this.word = word;
        this.allWord = word;
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

    @Override
    public int getCount() {
        return word.size();
    }

    public class ViewHolder {
        TextView txtWord, txtMean, txtType;
    }

//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        word.clear();
//        if (charText.length() == 0) {
//            word.addAll(filterWord);
//        }
//        else
//        {
//            for (WordQuizEntity w: filterWord) {
//                if (w.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
//                    word.add(w);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
@Override
public Filter getFilter() {
    if (valueFilter == null) {
        valueFilter = new ValueFilter();
    }
    return valueFilter;
}

    private class ValueFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            word = allWord;
            if (constraint != null && constraint.length() > 0) {
                ArrayList<WordQuizEntity> filterList = new ArrayList<WordQuizEntity>();
                for (int i = 0; i < word.size(); i++) {
                    if ((word.get(i).getName().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        WordQuizEntity w = new WordQuizEntity(word.get(i)
                                .getName(), word.get(i)
                                .getMeaning(), word.get(i).getType());
                        filterList.add(w);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = word.size();
                results.values = word;
            }
            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            word = (ArrayList<WordQuizEntity>) results.values;
            notifyDataSetChanged();
        }
    }
}
