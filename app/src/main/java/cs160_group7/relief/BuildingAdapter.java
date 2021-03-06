package cs160_group7.relief;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;


public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder> {

    private List<Building> buildingList;

    public BuildingAdapter(List<Building> bList) {
        this.buildingList = bList;
    }

    @Override
    public int getItemCount() {
        return buildingList.size();
    }

    // TODO: remove hardcoded location
    @Override
    public void onBindViewHolder(BuildingViewHolder BuildingViewHolder, int i) {
        final Building bi = buildingList.get(i);
        BuildingViewHolder.vName.setText(bi.name);
        double distance = Math.sqrt(Math.pow(37.8 - bi.latitude, 2.0) + Math.pow(-122.3 - bi.longitude, 2.0));
        BuildingViewHolder.vDistance.setText(new DecimalFormat("0.00#").format(distance));
        BuildingViewHolder.vRating.setText(new DecimalFormat("0.0#").format(bi.rating));
        BuildingViewHolder.vOpenHours.setText(bi.openHours);
        BuildingViewHolder.vBuildingImg.setImageDrawable(BuildingViewHolder.vBuildingImg.getContext().getDrawable(R.drawable.ic_launcher_background));

        BuildingViewHolder.vDetailsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailedView.class);
                intent.putExtra("name", bi.name);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public BuildingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new BuildingViewHolder(itemView);
    }

    public static class BuildingViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vDistance;
        protected TextView vRating;
        protected TextView vOpenHours;
        protected ImageView vBuildingImg;
        protected Button vDetailsButton;

        public BuildingViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.name);
            vDistance = (TextView) v.findViewById(R.id.distance);
            vRating = (TextView)  v.findViewById(R.id.rating);
            vOpenHours = (TextView) v.findViewById(R.id.openHours);
            vBuildingImg = (ImageView) v.findViewById(R.id.buildingImage);
            vDetailsButton = (Button) v.findViewById(R.id.detailsButton);
        }
    }
}

