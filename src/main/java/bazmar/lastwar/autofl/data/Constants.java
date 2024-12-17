package bazmar.lastwar.autofl.data;

public class Constants {

	public static final String PROJECT_RESOURCES_PATH = "/";
	public static final String PROJECT_IMAGES_PATH = PROJECT_RESOURCES_PATH + "images/";

	/**
	 * PATHS
	 */
	public static final String SANDBOX_PATH = "./tmp/";
	public static final String MOUSE_SCREEN_PATH = SANDBOX_PATH + "mouse/";
	public static final String DEBUG_SCREEN_PATH = SANDBOX_PATH + "debug/";
	public static final String TMP_SCREEN_PATH = SANDBOX_PATH + "tmp/";
	public static final String KICK_SCREEN_PATH = SANDBOX_PATH + "kick/";
	public static final String STATS_PATH = SANDBOX_PATH + "stats.ser";

	/**
	 * Image default diff marging
	 */
	public static final int SIMILARITY_THRESHOLD = 50;

	/**
	 * 0=principal 1=secondary
	 */
	public static final int SCREEN_INDEX_PRINCIPAL = 0;
	public static final int SCREEN_INDEX_SECONDARY = 1;

	public static final Context DEFAULT_CONTEXT = new Context(0, 0, 1920, 1080, Constants.SCREEN_INDEX_PRINCIPAL,
			BotType.SCREEN1);
	public static final Context DEFAULT_CONTEXT_2 = new Context(0, 0, 1920, 1080, Constants.SCREEN_INDEX_SECONDARY,
			BotType.SCREEN2);

	/**
	 * Images
	 */
	public static final String PNG_BLUESTACK = Constants.PROJECT_IMAGES_PATH + "init/bluestack.png";
	public static final String PNG_BLUESTACK_FL = Constants.PROJECT_IMAGES_PATH + "init/bluestack_fl.png";
	public static final String PNG_BLUESTACK_FULLSCREEN = Constants.PROJECT_IMAGES_PATH
			+ "init/bluestack_fullscreen.png";
	public static final String PNG_BIG_BLEUSTACK_MENU = Constants.PROJECT_IMAGES_PATH + "init/big_bluestack_menu.png";

	public static final String PNG_RETURN_BLUESTACK = Constants.PROJECT_IMAGES_PATH + "init/return_bluestack.png";
	public static final String PNG_RETURN_BLUESTACK_HOOVER = Constants.PROJECT_IMAGES_PATH
			+ "init/return_bluestack_hoover.png";

	public static final String PNG_WORLD = Constants.PROJECT_IMAGES_PATH + "world.png";
	public static final String PNG_BASE_ZOOMED = Constants.PROJECT_IMAGES_PATH + "base_zoomed.png";
	public static final String PNG_BASE_NOT_ZOOMED = Constants.PROJECT_IMAGES_PATH + "base_not_zoomed.png";
	public static final String PNG_BUTTON_BLUE = Constants.PROJECT_IMAGES_PATH + "button/blue.png";
	public static final String PNG_BUTTON_YELLOW = Constants.PROJECT_IMAGES_PATH + "button/yellow.png";

	public static final String PNG_FL_TITLE = Constants.PROJECT_IMAGES_PATH + "fl/title.png";
	public static final String PNG_FL_TITLE8 = Constants.PROJECT_IMAGES_PATH + "fl/title8.png";
	public static final String PNG_FL_ZERO = Constants.PROJECT_IMAGES_PATH + "fl/zero.png";
	public static final String PNG_FL_ZERO2 = Constants.PROJECT_IMAGES_PATH + "fl/zero2.png";
	public static final String PNG_FL_ZERO_WHITE = Constants.PROJECT_IMAGES_PATH + "fl/zero_white.png";
	public static final String PNG_FL_ZERO_WHITE2 = Constants.PROJECT_IMAGES_PATH + "fl/zero_white2.png";
	public static final String PNG_FL_ZERO_WHITE3 = Constants.PROJECT_IMAGES_PATH + "fl/zero_white3.png";
	public static final String PNG_FL_REJECT = Constants.PROJECT_IMAGES_PATH + "fl/reject.png";
	public static final String PNG_FL_ACCEPT = Constants.PROJECT_IMAGES_PATH + "fl/accept.png";
	public static final String PNG_FL_FULL_LIST = Constants.PROJECT_IMAGES_PATH + "fl/full_list.png";
	public static final String PNG_FL_LIST_EXIST = Constants.PROJECT_IMAGES_PATH + "fl/list_exist.png";
	public static final String PNG_FL_NOTIF = Constants.PROJECT_IMAGES_PATH + "fl/notif.png";
	public static final String PNG_FL_VACANT = Constants.PROJECT_IMAGES_PATH + "fl/vacant.png";
	public static final String PNG_FL_VACANT2 = Constants.PROJECT_IMAGES_PATH + "fl/vacant2.png";
	public static final String PNG_FL_50 = Constants.PROJECT_IMAGES_PATH + "fl/50.png";
	public static final String PNG_FL_CONQUERANT = Constants.PROJECT_IMAGES_PATH + "fl/conquerant.png";
	public static final String PNG_FL_COMMANADANT_MILITAIRE = Constants.PROJECT_IMAGES_PATH
			+ "fl/commandantmilitaire.png";
}
