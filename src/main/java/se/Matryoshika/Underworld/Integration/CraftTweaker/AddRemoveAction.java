package se.Matryoshika.Underworld.Integration.CraftTweaker;

import crafttweaker.IAction;

abstract public class AddRemoveAction {

	private final class Add implements IAction {

		@Override
		public void apply() {
			add();
		}

		@Override
		public String describe() {
			return String.format("Adding %s recipe: %s", getRecipeType(), getDescription());
		}
	}

	private final class Remove implements IAction {
		@Override
		public void apply() {
			remove();
		}

		@Override
		public String describe() {
			return String.format("Removing %s recipe: %s", getRecipeType(), getDescription());
		}
	}

	public final IAction action_add = new Add();
	public final IAction action_remove = new Remove();

	abstract protected void add();

	abstract protected void remove();

	abstract public String getDescription();

	abstract public String getRecipeType();

}
