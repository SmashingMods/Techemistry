import json
import shutil

group_1_elements = ["Li", "Na", "K", "Rb", "Cs"]
group_1_formulas = {
    "2% + 2H2O": "2%OH + 2H",
    "HCl + %OH": "%Cl + H2O",
    "HNO3 + %OH": "%NO3 + H2O",
    "H2SO4 + 2%OH": "%2SO4 + 2H2O",
    "2HCl + %2CO3": "2%Cl + H2O + CO2",
    "2HNO3 + %2CO3": "2%NO3 + H2O + CO2",
    "H2SO4 + %2CO3": "%2SO4 + H2O + CO2"
}

group_2_elements = ["Be", "Mg", "Ca", "Sr", "Ba"]
group_2_formulas = {
    "%O + H2O": "%OH2",
    "2HCl + %OH2": "%Cl2 + 2H2O",
    "2HNO3 + %OH2": "%NO32 + 2H2O",
    "H2SO4 + %OH2": "%SO4 + 2H2O",
    "2HCl + %CO3": "%Cl2 + H2O + CO2",
    "2HNO3 + %CO3": "%NO32 + H2O + CO2",
    "H2SO4 + %CO3": "%SO4 + H2O + CO2"
}

# addRecipe(ele + "O + H2O -> " + ele + "OH2", 273);
# addRecipe("2HCl + " + ele + "OH2 -> " + ele + "Cl2 + 2H2O", 273);
# addRecipe("2HNO3 + " + ele + "OH2 -> " + ele + "NO32 + 2H2O", 273);
# addRecipe("H2SO4 + " + ele + "OH2 -> " + ele + "SO4 + 2H2O", 273);

# addRecipe("2HCl + " + ele + "CO3 -> " + ele + "Cl2 + H2O + CO2", 273);
# addRecipe("2HNO3 + " + ele + "CO3 -> " + ele + "NO32 + H2O + CO2", 273);
# addRecipe("H2SO4 + " + ele + "CO3 -> " + ele + "SO4 + H2O + CO2", 273);

for ele in group_1_elements:
    for left, right in group_1_formulas.items():
        title = left.replace("%", ele)
        formula = title + " -> " + right.replace("%", ele)
        file = open("pygen/recipes/" + title.lower().replace(" ", "").replace("+", "_") + ".json", "w")
        file.write("""{
    "type": "techemistry:reaction_chamber",
    "group": "minecraft:misc",
    "formula": "%s",
    "minimumTemp": 273
}""" % formula)
        file.close()
        print("Generated recipes/" + title + ".json")

for ele in group_2_elements:
    for left, right in group_2_formulas.items():
        title = left.replace("%", ele)
        formula = title + " -> " + right.replace("%", ele)
        file = open("pygen/recipes/" + title.lower().replace(" ", "").replace("+", "_") + ".json", "w")
        file.write("""{
    "type": "techemistry:reaction_chamber",
    "group": "minecraft:misc",
    "formula": "%s",
    "minimumTemp": 273
}""" % formula)
        file.close()
        print("Generated recipes/" + title + ".json")
