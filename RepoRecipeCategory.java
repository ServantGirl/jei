package ServantGirl.DumpsterDiving.handlers.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import ServantGirl.DumpsterDiving.handlers.jei.jeirepoRecipe;
import net.minecraft.client.Minecraft;

public class RepoRecipeCategory extends repurRecipeCategory <jeirepoRecipe> {
	private final IDrawable background;
	private final String localizedName;

	public RepoRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper);
		background = guiHelper.createDrawable(jeiPlugin.RECIPE_GUI_VANILLA, 0, 114, 82, 54);
		localizedName = ("Repurposer");
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		animatedFlame.draw(minecraft, 1, 20);
		arrow.draw(minecraft, 24, 18);
	}

	@Override
	public String getTitle() {
		return localizedName;
	}

	@Override
	public String getModName() {
		return jeiPlugin.minecraftModName;
	}

	@Override
	public String getUid() {
		return DDRecipeCategoryUid.REPURPOSER;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, jeirepoRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(inputSlot, true, 0, 0);
		guiItemStacks.init(outputSlot, false, 60, 18);

		guiItemStacks.set(ingredients);
	}
}
