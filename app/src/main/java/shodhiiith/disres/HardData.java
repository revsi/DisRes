package shodhiiith.disres;

import android.location.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alpha on 4/4/15.
 */
public class HardData {
    public Address setAddress(double latit, double longit){
        Address spot = new Address(null);
        spot.setLatitude(latit);
        spot.setLongitude(longit);
        return spot;
    }
    public Map<String, List<Address>> getHardCodedData(){
        List<Address> affectedAreas = new ArrayList<Address>();
       // affectedAreas.add(setAddress(-----, -----));
        List<Address> healthCare = new ArrayList<Address>();
        healthCare.add(setAddress(17.44219, 78.3587));
        healthCare.add(setAddress(17.450219, 78.364239));
        healthCare.add(setAddress(17.441582, 78.521827));
        healthCare.add(setAddress(17.372717, 78.509651));
        healthCare.add(setAddress(17.447778, 78.508969));
        healthCare.add(setAddress(17.368354, 78.467252));
        healthCare.add(setAddress(17.453568, 78.413049));
        healthCare.add(setAddress(17.487143, 78.382018));
        healthCare.add(setAddress(17.349169, 78.497438));
        healthCare.add(setAddress(17.350667, 78.507907));
        healthCare.add(setAddress(17.344930, 78.509123));
        healthCare.add(setAddress(17.342413, 78.507041));
        healthCare.add(setAddress(17.415786, 78.413586));
        healthCare.add(setAddress(17.494156, 78.403119));
        healthCare.add(setAddress(17.439616, 78.504781));
        healthCare.add(setAddress(17.374419, 78.505923));
        healthCare.add(setAddress(17.374419, 78.505923));
        healthCare.add(setAddress(17.398503, 78.481338));
        healthCare.add(setAddress(17.492588, 78.315950));
        healthCare.add(setAddress(17.353700, 78.506793));
        healthCare.add(setAddress(17.359253, 78.514222));
        healthCare.add(setAddress(17.351114, 78.509839));
        healthCare.add(setAddress(17.361984, 78.506851));
        healthCare.add(setAddress(17.444531, 78.469375));
        healthCare.add(setAddress(17.350169, 78.512163));
        healthCare.add(setAddress(17.347909, 78.502158));
        healthCare.add(setAddress(17.430638, 78.422846));
        healthCare.add(setAddress(17.431489, 78.446775));
        healthCare.add(setAddress(17.444280, 78.386116));
        healthCare.add(setAddress(17.445871, 78.386437));
        healthCare.add(setAddress(17.420983, 78.456454));
        healthCare.add(setAddress(17.431214, 78.455385));
        healthCare.add(setAddress(17.429146, 78.456661));
        healthCare.add(setAddress(17.427431, 78.460713));
        healthCare.add(setAddress(17.393334, 78.426250));
        healthCare.add(setAddress(17.398624, 78.461073));
        healthCare.add(setAddress(17.395980, 78.465560));
        healthCare.add(setAddress(17.406292, 78.475910));
        healthCare.add(setAddress(17.401952, 78.462482));
        healthCare.add(setAddress(17.394426, 78.462718));
        healthCare.add(setAddress(17.383632, 78.466961));
        healthCare.add(setAddress(17.356419, 78.474275));
        healthCare.add(setAddress(17.356371, 78.476157));
        healthCare.add(setAddress(17.439040, 78.440634));
        healthCare.add(setAddress(17.440252, 78.497572));
        healthCare.add(setAddress(17.436452, 78.496180));
        healthCare.add(setAddress(17.438709, 78.498503));
        healthCare.add(setAddress(17.479447, 78.553356));
        healthCare.add(setAddress(17.393641, 78.440021));
        healthCare.add(setAddress(17.348899, 78.513492));
        healthCare.add(setAddress(17.393641, 78.440027));
        healthCare.add(setAddress(17.494909, 78.457370));
        List<Address> bloodBanks = new ArrayList<Address>();
        //bloodBanks.add(setAddress(----, ----));
        List<Address> fireBrigade = new ArrayList<Address>();
        fireBrigade.add(setAddress(17.379531,78.479018));
        fireBrigade.add(setAddress(17.368408,78.50190599999996));
        fireBrigade.add(setAddress(17.359544,78.47664399999996));
        fireBrigade.add(setAddress(17.353086,78.45923800000003));
        fireBrigade.add(setAddress(17.4082072,78.50605719999999));
        fireBrigade.add(setAddress(17.422485,78.53837799999997));
        fireBrigade.add(setAddress(17.4526898,78.44343290000006));
        fireBrigade.add(setAddress(17.4425354,78.50501780000002));
        fireBrigade.add(setAddress(17.4264979,78.45113220000007));
        fireBrigade.add(setAddress(17.378222,78.42137000000002));
        List<Address> police = new ArrayList<Address>();
        police.add(setAddress(17.388824, 78.476221));
        police.add(setAddress(17.389566, 78.452407));
        police.add(setAddress(17.375405, 78.475023));
        police.add(setAddress(17.542808, 78.356755));
        police.add(setAddress(17.324896, 78.479699));
        police.add(setAddress(17.408973, 78.497502));
        police.add(setAddress(17.334634, 78.468671));
        police.add(setAddress(17.437414, 78.504472));
        police.add(setAddress(17.357840, 78.464401));
        police.add(setAddress(17.345487, 78.454172));
        police.add(setAddress(17.461918, 78.500547));
        police.add(setAddress(17.378227, 78.421408));
        police.add(setAddress(17.370514, 78.512011));
        police.add(setAddress(17.451400, 78.505408));
        police.add(setAddress(17.419430, 78.500195));
        police.add(setAddress(17.395474, 78.490621));
        police.add(setAddress(17.436365, 78.488484));
        police.add(setAddress(17.402746, 78.462786));
        police.add(setAddress(17.346816, 78.470617));
        police.add(setAddress(17.446046, 78.352011));
        List<Address> ngo = new ArrayList<Address>();
        ngo.add(setAddress(17.388790, 78.481913));
        ngo.add(setAddress(17.438447, 78.458691));
        ngo.add(setAddress(17.429629, 78.510661));
        ngo.add(setAddress(17.433451, 78.428792));
        ngo.add(setAddress(17.385045, 78.486671));
        ngo.add(setAddress(17.445449, 78.510708));
        ngo.add(setAddress(17.440612, 78.505145));
        ngo.add(setAddress(17.386752, 78.479921));
        ngo.add(setAddress(17.483785, 78.543522));
        ngo.add(setAddress(17.411325, 78.424408));
        ngo.add(setAddress(17.399965, 78.448547));
        ngo.add(setAddress(17.394900, 78.454327));
        ngo.add(setAddress(17.389126, 78.473305));
        ngo.add(setAddress(17.392730, 78.474526));
        ngo.add(setAddress(17.349429, 78.387066));
        ngo.add(setAddress(17.428573, 78.537146));
        ngo.add(setAddress(17.353005, 78.485849));
        ngo.add(setAddress(17.391513, 78.497213));
        ngo.add(setAddress(17.394555, 78.498527));
        ngo.add(setAddress(17.437184, 78.439218));
        Map<String, List<Address>> hotSpots = new HashMap<String, List<Address>>();
        hotSpots.put("Affected", affectedAreas);
        hotSpots.put("Hospital", healthCare);
        hotSpots.put("Blood Bank", bloodBanks);
        hotSpots.put("Fire Brigade", fireBrigade);
        hotSpots.put("Police", police);
        hotSpots.put("NGO & Rescue", ngo);
        return hotSpots;
    }
}
