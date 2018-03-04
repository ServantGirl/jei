package ServantGirl.DumpsterDiving.handlers.jei;

import java.util.IllegalFormatException;
import java.util.Locale;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;


import ServantGirl.DumpsterDiving.init.BlockInit;
import ServantGirl.DumpsterDiving.init.blocks.CustomOre;
import ServantGirl.DumpsterDiving.init.blocks.Repuroser.containtrashfurn;
import ServantGirl.DumpsterDiving.init.blocks.Repuroser.guitrasfurn;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.FluidStack;
@JEIPlugin
public class jeiPlugin implements IModPlugin {

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		final IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		registry.addRecipeCategories(
				new RepoRecipeCategory(guiHelper)
		);
	}
	
	@Override
	public void register(IModRegistry registry) {
		final IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		registry.addDescription(new ItemStack(BlockInit.trash_ore), "Who knows what you'll find in the garbage? Maybe useful scrap? Maybe just rotten food. Melt or break down these scraps into useful resources!");
		registry.addDescription(new ItemStack(BlockInit.trash_furn), "Increase your resource yield Garbage blocks. Uses rotten food as fuel.");
		//registry.addIngredientInfo(BlockInit.trash_ore, BlockInit.class, "Tis banana test");
		registry.addRecipes(RepoRecipeMaker.getFurnaceRecipes(jeiHelpers), DDRecipeCategoryUid.REPURPOSER);
		
		registry.addRecipeClickArea(guitrasfurn.class, 78, 22, 28, 23, DDRecipeCategoryUid.REPURPOSER);

		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

		recipeTransferRegistry.addRecipeTransferHandler(containtrashfurn.class, DDRecipeCategoryUid.REPURPOSER, 0, 1, 3, 36);

		registry.addRecipeCatalyst(new ItemStack(BlockInit.trash_furn), DDRecipeCategoryUid.REPURPOSER);
	}
	public static final String MOD_ID = "dd";
	public static final String TEXTURE_GUI_PATH = "textures/gui/";
	public static final String minecraftModName = "Dumpster Diving";
	public static final String RESOURCE_DOMAIN = MOD_ID.toLowerCase(Locale.ENGLISH);
	public static final String TEXTURE_GUI_VANILLA = jeiPlugin.TEXTURE_GUI_PATH + "gui_vanilla.png";
public static final ResourceLocation RECIPE_GUI_VANILLA = new ResourceLocation(RESOURCE_DOMAIN, TEXTURE_GUI_VANILLA);

public static String translateToLocal(String key) {
	if (I18n.canTranslate(key)) {
		return I18n.translateToLocal(key);
	} else {
		return I18n.translateToFallback(key);
	}
}
public static String translateToLocalFormatted(String key, Object... format) {
	String s = translateToLocal(key);
	try {
		return String.format(s, format);
	} catch (IllegalFormatException e) {
		//Log.get().error("Format error: {}", s, e);
		return "Format error: " + s;
	}
}
}