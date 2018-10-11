package app.ice.logingmail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    List<Notes> notesList;
    Context context;

    public NotesAdapter(Context context,List<Notes> notes){
        this.context=context;
        this.notesList=notes;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.count.setText(String.valueOf(notesList.get(position).count));
        holder.name.setText(notesList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView count,name;
        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card);
            count= view.findViewById(R.id.count);
            name= view.findViewById(R.id.name);
        }
    }
}
