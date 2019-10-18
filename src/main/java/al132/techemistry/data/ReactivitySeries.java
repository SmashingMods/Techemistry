package al132.techemistry.data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReactivitySeries {

    private static List<String> reactivitySeries;

    public static List<String> getSeriesThrough(String stack) {
        String match = reactivitySeries.stream().filter(i -> i.equals(stack)).findFirst().orElse(null);
        if (match == null) return Collections.emptyList();
        return reactivitySeries.subList(0, reactivitySeries.indexOf(match) + 1);
    }

    public static void init() {
        reactivitySeries = Stream.of(
                "cesium",
                "francium",
                "rubidium",
                "potassium",
                "sodium",
                "lithium",
                "barium",
                "radium",
                "strontium",
                "calcium",
                "magnesium",
                "beryllium",
                "aluminum",
                "titanium",
                "manganese",
                "zinc",
                "chromium",
                "iron",
                "cadmium",
                "cobalt",
                "nickel",
                "tin",
                "lead",
                "antimony",
                "bismuth",
                "copper",
                "tungsten",
                "mercury",
                "silver",
                "gold",
                "platinum"
        ).collect(Collectors.toList());
    }
}
