### 0.3.12: Recipes by the Campfire

- Updated Mob-Spawning mechanics.
   - Squids are now being force-spawned manually.
   - Disabling force-spawn for Roots entities.
   - More than doubled amount of mobs allowed to co-exist in a 64x128x64 area (7-16).
- Updated JEI support to 3.14.7.416.
- Added MineTweaker Support for the Metamorphic Table.
   - Read [here](https://github.com/Matryoshika/Underworld/wiki/MineTweaker-Support) to learn how to add and remove recipes.
- Changed how the Sugar Pile's interact with hostile entities.
   - Removed the "Fireflies push hostiles away" ability.
   - Made Fireflies "daze" hostiles instead. Hostile entities will now freeze in place and watch the Fireflies.
- Trimmed the chance for Moss-Stone to spawn, from 5% to 4%.
- Removed the Sugar Pile from the creative tabs. Right-clicking sugar on the ground will still place them.
   - Placing the *sugar* down is vital to the pile's interaction with hostiles. Placing the *pile* down, directly circumvented it.

---
### 0.2.12: Summer winds, Fireflies, Bugspray 4k

- Added Anomalous Dirt to Metamorphic Table recipes.
   - Changes itself depending on biome/nearby blocks. Also used in WorldGen.
- Added config-option to allow time to move normally, or be frozen at noon.
- Added config-option to make Underworld slightly less dark
- Added the Sugar Pile block.
   - Spawns Fireflies above it.
   - Aside from being very cosmetic, also protects you against hostile mobs.
- Hopefully fixed console spam for the last time. Restricted console output even more, and added a timer between messages.
- Added Moss Stone. Mossy variant of normal Stone. Usurps the name from Vanilla's cobblestone variant.
   - Added Firefly Moss Stone. Slightly glowing moss stone that occasionally shows a Firefly. Will be used more later.
- Started working on infrastructure for porting migraine inducing buildings, like villages & ocean temples, to easier-to-understand-and-use collections of blockstates.

---
### Addendum 0.2.12a

- Moved Client-side events from Common code.
- Added Stone resistance & hardness to Moss Stone and it's Firefly variant.
- Fixed Sugar not placing Sugar Piles.

---
### 0.1.12: Two-way ticket, please

- Added the Ender Porter
   - Takes one to and from The End, at the cost of Eye of Ender's.
- Added config-option for Pack-creators to force Underworld generation, no matter what WorldType was chosen
- Fixed broken (missing) crafting recipes.

---
### 0.1.11: A little to the left... A little to the right...

- Added the Metamorphic Table.
   - Transmutates certain things to similar items.
   - Requires a small activation ritual. Put a redstone block under the table to get some hints.
   - Be sure where you want it! Break it and you have to activate it again.
- Added Configs en-masse.
   - Configs for enabling/disabling every item, block, and even worldgen features of Underworld!
   - **Warning** Disabling items or blocks will cause all pre-existing ones to disappear.
   - Added config for editing what block (including meta value) should be used to activate the Metamorphic table. Allows easier/harder access to it in packs.
- Added JEI integration to view the Metamorphic Table's recipes, if JEI is present.

---
### 0.0.11: Bug-spray 2K!

- Fixed duplicate listing of Underworld when selecting worlds.
- Fixed the Lantern flickering when held in the offhand or when moving at high speeds.

---
### 0.0.10: Sugar and... coal?

- Added Sugarbeets: Custom sugarcane-copycat that can be planted on dirt, grass & sand.
- Added grass-drop for Sugarbeets! Go hit them grass.
- Added wildcard value to the Brazier's recipe: Now allows charcoal to be used as well.
- Removed the horrid Sugarcane worldgen implemented in 0.0.8.

---
### 0.0.9: Quiet!

- Hotfix: Fixed an issue where console would be spammed if Underworld's mob-spawning system would try to spawn a non-standard Animal.
- Removed a few debugging prints.

---
### 0.0.8: Bug Off!

- Should have fixed an issue where hundreds of Guardians would spawn in matter of minutes.
- Fixed the spawn-platform, dungeons & lilypad worldgen bleeding into other dimensions & worldtypes.
- Added Sugarcane to worldgen: Still quite rare. Might switch to custom sugar-cane variant instead. Any more often and sugarcane will plummet 
WorldGen to a standstill.

---
### 0.0.7: Treasures ahoy!

- Fixed Lantern recipe.
- Added Dungeons (Rooms size 7 to 11 blocks wide, deep & tall.) Spawns high and low.
- Added complimentary lootchests to Dungeons.
- Made Animals spawn a bit more frequently.
- Added Lilypads to worldgen, spawns in group of 8 to 16.
- Added base for spawning in Ocean Monuments & Villages in worldgen. Not currently implemented!

---
### 0.0.6: Let There Be Life!
- Added Chest with loot in each hut.
- Added the Invisible Mob-Spawner. Makes use of Extremely Dark Voodoo to spawn each possible Animal, where it should, even in utter and 
complete darkness.
- Added Invisible Mob-Spawner to WorldGen.
- Fixed doors in huts starting out as closed.
- Fixed SpawnPoint of Underworld maps.
- Fixed Forge yelling about Textures/Models missing.

---
### 0.0.5 : Let There Be Light!
- Added the DDD: Creative-Only item to help examine BlockStates.
- Added the Brazier: A decorative lightsource akin to torches; Requires flint and steel to light.
- Added the Lantern: A held item that lights up your surroundings!
- Added Clay to worldgen: Spawns in oblong spheres under y = 32.
- Updated Huts to have 1 Brazier placed outside their door.
- Updated Anomalous Dirt to place grass on itself, and a layer of snow over that.
- Fixed Underworld's WorldProvider to replicate vanilla WorldProvider if the WorldType is not Underworld.

---
### 0.0.4

- Added Anomalous Dirt
- Worldgen now places Anomalous Dirt instead of Vanilla: Updates itself to change to Grass, Sand or Snow dependant on the Biome it is in. 
Major performance increase compared to before
- Sand blocks in worldgen are placed on top of a layer of sandstone: prevents the sand from falling; Major performance increase compared to 
the falling blocks.
- Added Huts to worldgen

---
### 0.0.3

- Changed tree worldgen to also use Birch, Acacia & Dark oak
- Trees now spawn in groups of up to 5 at a time
- Hang Vines only spawn in "Warm" biomes.

---
### 0.0.2

- Fixed Biome-sizes
- Added Oak-trees to worldgen
- Added grassy areas beneath the trees
- Added Hang Vine block to worldgen

---
### 0.0.1
- Initial Commit.
- Basic worldgen, nothing special.
