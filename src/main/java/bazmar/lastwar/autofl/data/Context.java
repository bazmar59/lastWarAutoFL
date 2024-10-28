package bazmar.lastwar.autofl.data;

public record Context(int origX, int origY, int width, int height, int screenIndex, BotType botType) {

	public Context withScreenIndex(int newScreenIndex) {
		return new Context(this.origX, this.origY, this.width, this.height, newScreenIndex, this.botType);
	}
}