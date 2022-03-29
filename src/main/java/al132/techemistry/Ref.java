package al132.techemistry;

import al132.chemlib.Utils;
import al132.chemlib.chemistry.ChemicalStack;
import al132.techemistry.items.BaseItem;
import al132.techemistry.items.minerals.Mineral;
import al132.techemistry.items.minerals.MineralItem;
import al132.techemistry.items.misc.*;
import al132.techemistry.items.parts.ElectrodeItem;
import al132.techemistry.items.parts.GearItem;
import al132.techemistry.items.parts.PartMaterial;
import al132.techemistry.items.parts.PartMaterialRegistry;
import net.minecraft.world.food.FoodProperties;

import java.util.ArrayList;
import java.util.List;

public class Ref {

    public final static int TEXT_COLOR = 16777215;

    public static final List<GearItem> gears = new ArrayList<>();



    public static List<Mineral> minerals = new ArrayList<>();
    //sulfide
    public static Mineral galena;
    public static Mineral pyrite;
    public static Mineral sphalerite;
    public static Mineral chalcocite;
    public static Mineral cinnabar;
    public static Mineral stibnite;
    public static Mineral millerite;
    public static Mineral braggite;

    //oxide
    public static Mineral cassiterite;
    public static Mineral pyrolusite;
    public static Mineral cuprite;
    public static Mineral ilmenite;
    public static Mineral magnetite;
    public static Mineral hematite;
    public static Mineral uraninite;

    //carbonate
    public static Mineral strontianite;
    public static Mineral rhodochrosite;
    public static Mineral cerussite;
    public static Mineral siderite;
    public static Mineral spherocobaltite;

    //phosphate
    public static Mineral vanadinite;
    public static Mineral pyromorphite;

    //sulfate
    public static Mineral melanterite;
    public static Mineral barite;
    public static Mineral celestite;
    public static Mineral anglesite;


    public static String s(int input) {
        return Utils.getSubscript(input);
    }


    public static void initItems() {
        galena = new Mineral("galena", new ChemicalStack("lead_oxide"));
        pyrite = new Mineral("pyrite", new ChemicalStack("iron_disulfide"));
        chalcocite = new Mineral("chalcocite", new ChemicalStack("copper", 2), new ChemicalStack("sulfur"));
        sphalerite = new Mineral("sphalerite", new ChemicalStack("zinc_sulfide"));
        cinnabar = new Mineral("cinnabar", new ChemicalStack("mercury_sulfide"));
        stibnite = new Mineral("stibnite", new ChemicalStack("antimony_trisulfide"));
        millerite = new Mineral("millerite", new ChemicalStack("nickel_sulfide"));
        braggite = new Mineral("braggite", "(Pt, Pd, Ni)S");

        cassiterite = new Mineral("cassiterite", new ChemicalStack("tin_oxide"));
        pyrolusite = new Mineral("pyrolusite", new ChemicalStack("manganese_oxide"));
        strontianite = new Mineral("strontianite", new ChemicalStack("strontium_carbonate"));
        vanadinite = new Mineral("vanadinite", "Pb", s(5), "(VO", s(4), ")", s(3), "Cl");
        pyromorphite = new Mineral("pyromorphite", "Pb", s(5), "(PO", s(4), ")", s(3), "Cl");
        hematite = new Mineral("hematite", new ChemicalStack("iron_oxide"));
        ilmenite = new Mineral("ilmenite", "FeTiO" + s(3));
        cuprite = new Mineral("cuprite", "Cu" + s(2) + "O");
        magnetite = new Mineral("magnetite", "Fe" + s(3) + "O" + s(4));
        uraninite = new Mineral("uraninite", "UO" + s(2));
        rhodochrosite = new Mineral("rhodochrosite", new ChemicalStack("manganese_carbonate"));
        cerussite = new Mineral("cerussite", new ChemicalStack("lead_carbonate"));
        siderite = new Mineral("siderite", new ChemicalStack("iron_carbonate"));
        spherocobaltite = new Mineral("spherocobaltite", new ChemicalStack("cobalt_carbonate"));
        //SULFATE
        melanterite = new Mineral("melanterite", "FeSO", s(4), "7H", s(2), "O");
        barite = new Mineral("barite", new ChemicalStack("barium_sulfate"));
        celestite = new Mineral("celestite", new ChemicalStack("strontium_sulfate"));
        anglesite = new Mineral("anglesite", new ChemicalStack("lead_sulfate"));

    }
}