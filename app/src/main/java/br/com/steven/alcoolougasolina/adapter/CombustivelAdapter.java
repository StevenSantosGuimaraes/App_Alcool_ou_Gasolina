package br.com.steven.alcoolougasolina.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import br.com.steven.alcoolougasolina.R;
import br.com.steven.alcoolougasolina.model.Combustivel;

public class CombustivelAdapter extends RecyclerView.Adapter<CombustivelAdapter.CombustivelViewHolder> {

    private List<Combustivel> combustivelList;
    private OnItemLongClickListener itemLongClickListener;

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public CombustivelAdapter(List<Combustivel> combustivelList, OnItemLongClickListener itemLongClickListener) {
        this.combustivelList = combustivelList;
        this.itemLongClickListener = itemLongClickListener;
    }

    @Override
    public CombustivelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_combustivel, parent, false);
        return new CombustivelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CombustivelViewHolder holder, int position) {

        Combustivel combustivel = combustivelList.get(position);

        holder.nomeTextView.setText("Combustível: " + combustivel.getNomeCombustivel());
        holder.precoTextView.setText("Preço: R$ " + String.valueOf(combustivel.getPrecoCombustivel()));
        holder.recomendacaoTextView.setText("Recomendação: " + combustivel.getRecomendacao());

        holder.itemView.setOnLongClickListener(view -> {
            if (itemLongClickListener != null) {
                itemLongClickListener.onItemLongClick(position);
            }
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return combustivelList.size();
    }

    public static class CombustivelViewHolder extends RecyclerView.ViewHolder {
        TextView nomeTextView;
        TextView precoTextView;
        TextView recomendacaoTextView;

        public CombustivelViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.nomeTextView);
            precoTextView = itemView.findViewById(R.id.precoTextView);
            recomendacaoTextView = itemView.findViewById(R.id.recomendacaoTextView);
        }

    }

}
