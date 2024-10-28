package bazmar.lastwar.autofl.data;

public class Zones {
	// FL
	public static Zone zoneFLGame;
	public static Zone zoneFLBaseOrWorld;
	public static Zone zoneFLStrat;
	public static Zone zoneFLSecu;
	public static Zone zoneFLDev;
	public static Zone zoneFLScience;
	public static Zone zoneFLInter;
	public static Zone zoneFLStratTime;
	public static Zone zoneFLSecuTime;
	public static Zone zoneFLDevTime;
	public static Zone zoneFLScienceTime;
	public static Zone zoneFLInterTime;
	public static Zone zoneFLAccept;
	public static Zone zoneFLList;
	public static Zone zoneFLClose;
	public static Zone zoneFL253;
	public static Zone zoneFLAvatar;
	public static Zone zoneFLTime;
	public static Zone zoneFLReject;
	public static Zone zoneFLConfirmReject;
	public static Zone zoneFLTextMonde;

	public static void loadZoneWithContextFL(Context context) {
		zoneFLGame = new Zone(0, 0, context.width(), context.height(), true, context, "zoneFLGame");
		zoneFLBaseOrWorld = new Zone(430, 900, 130, 100, true, context, "zoneFLBaseOrWorld");

		zoneFLStrat = new Zone(198, 374, (343 - 198), (570 - 374), true, context, "zoneFLStrat");
		zoneFLSecu = new Zone(370, 374, (510 - 370), (570 - 374), true, context, "zoneFLSecu");
		zoneFLDev = new Zone(30, 590, (170 - 30), (790 - 590), true, context, "zoneFLDev");
		zoneFLScience = new Zone(198, 590, (343 - 198), (790 - 590), true, context, "zoneFLScience");
		zoneFLInter = new Zone(370, 590, (510 - 370), (790 - 590), true, context, "zoneFLInter");

		zoneFLStratTime = new Zone(198, 540, (343 - 198), 30, true, context, "zoneFLStratTime");
		zoneFLSecuTime = new Zone(370, 540, (510 - 370), 30, true, context, "zoneFLSecuTime");
		zoneFLDevTime = new Zone(30, 760, (170 - 30), 30, true, context, "zoneFLDevTime");
		zoneFLScienceTime = new Zone(198, 760, (343 - 198), 30, true, context, "zoneFLScienceTime");
		zoneFLInterTime = new Zone(370, 760, (510 - 370), 30, true, context, "zoneFLInterTime");

		zoneFLAccept = new Zone(400, 205, (441 - 400), (243 - 205), true, context, "zoneFLAccept");
		zoneFLList = new Zone(470, 823, (500 - 470), (859 - 823), true, context, "zoneFLList");
		zoneFLClose = new Zone(503, 85, (531 - 503), (110 - 85), true, context, "zoneFLClose");
		zoneFL253 = new Zone(313, 650, (500 - 313), (720 - 650), true, context, "zoneFL253");
		zoneFLAvatar = new Zone(12, 13, (72 - 12), (73 - 13), true, context, "zoneFLAvatar");
		zoneFLTime = new Zone(327, 285, (436 - 327), (315 - 285), true, context, "zoneFLTime");
		zoneFLReject = new Zone(125, 821, (270 - 125), (877 - 821), true, context, "zoneFLReject");
		zoneFLConfirmReject = new Zone(119, 503, (265 - 119), (568 - 503), true, context, "zoneFLConfirmReject");

		zoneFLTextMonde = new Zone(245, 920, 5, 5, true, context, "zoneFLTextMonde");
	}

	public static Coord putInGameXYValues(Coord coordFoundInZone, Zone searchingZone) {
		if (coordFoundInZone == null || searchingZone == null) {
			return null;
		}
		Coord coordInGame;
		if (coordFoundInZone != null && searchingZone != null) {
			coordInGame = new Coord(coordFoundInZone.getX() + searchingZone.getX(),
					coordFoundInZone.getY() + searchingZone.getY(), coordFoundInZone.getWidth(),
					coordFoundInZone.getHeight(), coordFoundInZone.getName());
			return coordInGame;
		}
		return null;
	}
}
