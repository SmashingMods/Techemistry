package al132.techemistry.data;

import al132.chemlib.chemistry.ChemicalStack;
import al132.chemlib.items.IChemical;
import al132.techemistry.utils.TUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FormulaParser {

    private static IChemical lookup(String abbreviation) {
        Optional<IChemical> output = TUtils.getChemicalByAbbreviation(abbreviation);
        if (output.isPresent()) {
            return output.get();
        } else throw new RuntimeException("Unable to lookup chemical [" + abbreviation + "]");
    }

    private static ItemStack parseBit(String input) {
        int index = 0;
        int count = 1;
        IChemical chemical = null;
        while (index < input.length()) {
            char current = input.charAt(index);
            if (Character.isUpperCase(current)) {
                chemical = lookup(input.substring(index));
                index += input.substring(index).length();
            } else if (Character.isDigit(current)) {
                if (Character.isDigit(input.charAt(index + 1))) {
                    count = Integer.parseInt(input.substring(index, index + 2));
                    index++;
                } else count = Character.digit(current, 10);
            } else if (current != ' ' && current != '-' && current != '>' && current != '+') {
                throw new RuntimeException("Invalid character [" + current + "] input: [" + input + "]");
            }
            index++;
        }
        assert count > 0;
        return new ChemicalStack(chemical, count).toItemStack();
    }

    public static Formula parse(String formulaStr) {
        List<ItemStack> inputs = new ArrayList<>();
        List<ItemStack> outputs = new ArrayList<>();
        boolean parsingInput = true;
        List<String> bits = Arrays.asList(formulaStr.split(" "));
        for (String bit : bits) {
            if (bit.equals("->")) {
                parsingInput = false;
                continue;
            } else if (bit.equals("+")) continue;
            else {
                if (parsingInput) inputs.add(parseBit(bit));
                else outputs.add(parseBit(bit));
            }
        }
        return new Formula(inputs, outputs);
    }
}
