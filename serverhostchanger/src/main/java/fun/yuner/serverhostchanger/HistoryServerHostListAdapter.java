package fun.yuner.serverhostchanger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoryServerHostListAdapter extends BaseAdapter {
    private Context context;
    private List<String> historyServerHostList;
    private ViewHolder viewHolder;

    public HistoryServerHostListAdapter(Context context, List<String> historyServerHostList) {
        this.context = context;
        this.historyServerHostList = historyServerHostList;
    }

    @Override
    public int getCount() {
        return historyServerHostList == null ? 0 : historyServerHostList.size();
    }

    @Override
    public Object getItem(int position) {
        return historyServerHostList == null ? null : historyServerHostList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_history_server_host_lv, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.historyServerHostTv = convertView.findViewById(R.id.history_sever_host_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.historyServerHostTv.setText(historyServerHostList.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView historyServerHostTv;
    }
}
