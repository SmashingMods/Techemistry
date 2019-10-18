import json
import shutil

name = input("Item name?")
parsedName = name.replace('_', ' ').title()

#Model
model = open("src/main/resources/assets/techemistry/models/item/" + name + ".json", "w")
model.write("""{
	"parent": "item/generated",
	"textures": {
		"layer0": "techemistry:items/%s"
	}
}""" % name)
model.close()
print("Generated models/item/" + name + ".json")

#Lang
lang_path = "src/main/resources/assets/techemistry/lang/en_us.json"
lang_file = open(lang_path, 'r+', encoding='utf-8', errors='ignore', )  # as lang_data:
lang_data = json.load(lang_file, strict=False)
lang_data['item.techemistry.' + name] = parsedName
lang_data['item.techemistry.' + name + '.tooltip'] = ""
lang_file.seek(0)
json.dump(lang_data, lang_file, indent=4)
lang_file.truncate()
print("Generated entries in en_us.json")

#Image
image_dir = "src/main/resources/assets/techemistry/textures/items/"
shutil.copy(image_dir + "cassiterite.png", image_dir + name + ".png")
print("Generated default image in /textures/items/" + name + ".png")