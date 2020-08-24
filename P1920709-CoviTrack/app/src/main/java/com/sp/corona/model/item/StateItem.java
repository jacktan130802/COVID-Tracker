

package com.sp.corona.model.item;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.sp.corona.R;
import com.sp.corona.model.casetime.Statewise;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateItem extends AbstractItem<StateItem.ViewHolder> {

    private Statewise statewise;
    private String packageName;

    public StateItem(Statewise statewise) {
        this.statewise = statewise;
    }

    @NotNull
    @Override
    public ViewHolder getViewHolder(@NotNull View view) {
        return new ViewHolder(view);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_state;
    }

    @Override
    public int getType() {
        return R.id.fastadapter_item;
    }

    public static class ViewHolder extends FastItemAdapter.ViewHolder<StateItem> {
        @BindView(R.id.line1)
        AppCompatTextView line1;
        @BindView(R.id.line2)
        AppCompatTextView line2;
        @BindView(R.id.line3)
        AppCompatTextView line3;

        private Context context;

        ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
            context = view.getContext();
        }

        @Override
        public void bindView(@NotNull StateItem item, @NotNull List<?> list) {

            final Statewise statewise = item.getStatewise();

            line1.setText(statewise.getState());
            line2.setText(StringUtils.joinWith(" \u2022 ", "Today : Reported " + statewise.getConfirmed(), "Active " + statewise.getActive(), "Recovered " + statewise.getRecovered(), "Deaths " + statewise.getDeaths()));
            line3.setText(StringUtils.joinWith(" : ", "Last updated", statewise.getLastupdatedtime()));
        }

        @Override
        public void unbindView(@NotNull StateItem item) {
            line1.setText(null);
            line2.setText(null);
            line3.setText(null);
        }
    }
}
