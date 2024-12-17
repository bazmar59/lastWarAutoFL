package bazmar.lastwar.autofl.data;

public class Zones {
	// FL
	public static Zone zoneFLGame;
	public static Zone zoneFLBaseOrWorld;
	public static Zone zoneFLFL;
	public static Zone zoneFLStrat;
	public static Zone zoneFLSecu;
	public static Zone zoneFLDev;
	public static Zone zoneFLScience;
	public static Zone zoneFLInter;

	public static Zone zoneFLFLTime;
	public static Zone zoneFLStratTime;
	public static Zone zoneFLSecuTime;
	public static Zone zoneFLDevTime;
	public static Zone zoneFLScienceTime;
	public static Zone zoneFLInterTime;

	public static Zone zoneFLStrat8;
	public static Zone zoneFLSecu8;
	public static Zone zoneFLDev8;
	public static Zone zoneFLScience8;
	public static Zone zoneFLInter8;

	public static Zone zoneFLStrat8Time;
	public static Zone zoneFLSecu8Time;
	public static Zone zoneFLDev8Time;
	public static Zone zoneFLScience8Time;
	public static Zone zoneFLInter8Time;

	public static Zone zoneFLMilitaire8w;
	public static Zone zoneFLAdmin8w;
	public static Zone zoneFLStrat8w;
	public static Zone zoneFLSecu8w;
	public static Zone zoneFLDev8w;
	public static Zone zoneFLScience8w;
	public static Zone zoneFLInter8w;

	public static Zone zoneFLMilitaire8wTime;
	public static Zone zoneFLAdmin8wTime;
	public static Zone zoneFLStrat8wTime;
	public static Zone zoneFLSecu8wTime;
	public static Zone zoneFLDev8wTime;
	public static Zone zoneFLScience8wTime;
	public static Zone zoneFLInter8wTime;

	public static Zone zoneFLAccept;
	public static Zone zoneFLList;
	public static Zone zoneFLClose;
	public static Zone zoneFL253;
	public static Zone zoneFLAvatar;
	public static Zone zoneFLTime;
	public static Zone zoneFLReject;
	public static Zone zoneFLApply;
	public static Zone zoneFLConfirmReject;
	public static Zone zoneFL50;
	public static Zone zoneFLTextMonde;
	public static Zone zoneFLConquerant;
	public static Zone zoneFLCommandantMilitaireWin;

	public static void loadZoneWithContextFL(Context context) {
		zoneFLGame = new Zone(0, 0, context.width(), context.height(), true, context, "zoneFLGame");
		zoneFLBaseOrWorld = new Zone(430, 900, 130, 100, true, context, "zoneFLBaseOrWorld");

		// 6 buffs
		zoneFLFL = new Zone(30, 374, (170 - 30), 200, true, context, "zoneFLFL");
		zoneFLStrat = new Zone(198, 374, (343 - 198), 200, true, context, "zoneFLStrat");
		zoneFLSecu = new Zone(370, 374, (510 - 370), 200, true, context, "zoneFLSecu");
		zoneFLDev = new Zone(30, 590, (170 - 30), 200, true, context, "zoneFLDev");
		zoneFLScience = new Zone(198, 590, (343 - 198), 200, true, context, "zoneFLScience");
		zoneFLInter = new Zone(370, 590, (510 - 370), 200, true, context, "zoneFLInter");

		zoneFLFLTime = new Zone(30, 540, 145, 30, true, context, "zoneFLFLTime");
		zoneFLStratTime = new Zone(198, 540, 145, 30, true, context, "zoneFLStratTime");
		zoneFLSecuTime = new Zone(370, 540, 145, 30, true, context, "zoneFLSecuTime");
		zoneFLDevTime = new Zone(30, 760, 145, 30, true, context, "zoneFLDevTime");
		zoneFLScienceTime = new Zone(198, 760, 145, 30, true, context, "zoneFLScienceTime");
		zoneFLInterTime = new Zone(370, 760, 145, 30, true, context, "zoneFLInterTime");

		// 8 buffs loose
		zoneFLConquerant = new Zone(200, 100, 360 - 200, 10, true, context, "zoneFLConquerant");
		zoneFLStrat8 = new Zone(198, 374 + 80, (343 - 198), 200, true, context, "zoneFLStrat8");
		zoneFLSecu8 = new Zone(370, 374 + 80, (510 - 370), 200, true, context, "zoneFLSecu8");
		zoneFLDev8 = new Zone(30, 590 + 80, (170 - 30), 200, true, context, "zoneFLDev8");
		zoneFLScience8 = new Zone(198, 590 + 80, (343 - 198), 200, true, context, "zoneFLScience8");
		zoneFLInter8 = new Zone(370, 590 + 80, (510 - 370), 200, true, context, "zoneFLInter8");

		zoneFLStrat8Time = new Zone(198, 540 + 80, 145, 30, true, context, "zoneFLStrat8Time");
		zoneFLSecu8Time = new Zone(370, 540 + 80, 145, 30, true, context, "zoneFLSecu8Time");
		zoneFLDev8Time = new Zone(30, 760 + 80, 145, 30, true, context, "zoneFLDev8Time");
		zoneFLScience8Time = new Zone(198, 760 + 80, 145, 30, true, context, "zoneFLScience8Time");
		zoneFLInter8Time = new Zone(370, 760 + 80, 145, 30, true, context, "zoneFLInter8Time");

		// 8 buffs win
		zoneFLCommandantMilitaireWin = new Zone(185, 390, 20, 10, true, context, "zoneFLCommandantMilitaireWin");
		zoneFLMilitaire8w = new Zone(110, 243, (343 - 198), (570 - 374), true, context, "zoneFLMilitaire8w");
		zoneFLAdmin8w = new Zone(305, 243, (343 - 198), (570 - 374), true, context, "zoneFLAdmin8w");
		zoneFLStrat8w = new Zone(198, 374 + 80, (343 - 198), 200, true, context, "zoneFLStrat8w");
		zoneFLSecu8w = new Zone(370, 374 + 80, (510 - 370), 200, true, context, "zoneFLSecu8w");
		zoneFLDev8w = new Zone(30, 590 + 80, (170 - 30), 200, true, context, "zoneFLDev8w");
		zoneFLScience8w = new Zone(198, 590 + 80, (343 - 198), 200, true, context, "zoneFLScience8w");
		zoneFLInter8w = new Zone(370, 590 + 80, (510 - 370), 200, true, context, "zoneFLInter8w");

		zoneFLMilitaire8wTime = new Zone(147, 408, 145, 30, true, context, "zoneFLMilitaire8wTime");
		zoneFLAdmin8wTime = new Zone(338, 408, 145, 30, true, context, "zoneFLAdmin8wTime");
		zoneFLStrat8wTime = new Zone(198, 540 + 80, 145, 30, true, context, "zoneFLStrat8wTime");
		zoneFLSecu8wTime = new Zone(370, 540 + 80, 145, 30, true, context, "zoneFLSecu8wTime");
		zoneFLDev8wTime = new Zone(30, 760 + 80, 145, 30, true, context, "zoneFLDev8wTime");
		zoneFLScience8wTime = new Zone(198, 760 + 80, 145, 30, true, context, "zoneFLScience8wTime");
		zoneFLInter8wTime = new Zone(370, 760 + 80, 145, 30, true, context, "zoneFLInter8wTime");

		zoneFLAccept = new Zone(400, 205, (441 - 400), (243 - 205), true, context, "zoneFLAccept");
		zoneFLList = new Zone(470, 823, (500 - 470), (859 - 823), true, context, "zoneFLList");
		zoneFLClose = new Zone(503, 85, (531 - 503), (110 - 85), true, context, "zoneFLClose");
		zoneFL253 = new Zone(313, 650, (500 - 313), (720 - 650), true, context, "zoneFL253");
		zoneFLAvatar = new Zone(12, 13, (72 - 12), (73 - 13), true, context, "zoneFLAvatar");
		zoneFLTime = new Zone(327, 285, (436 - 327), (315 - 285), true, context, "zoneFLTime");
		zoneFLReject = new Zone(125, 821, (270 - 125), (877 - 821), true, context, "zoneFLReject");
		zoneFLConfirmReject = new Zone(119, 503, (265 - 119), (568 - 503), true, context, "zoneFLConfirmReject");

		zoneFLApply = new Zone(211, 824, (353 - 211), (875 - 824), true, context, "zoneFLApply");

		zoneFL50 = new Zone(385, 430, (411 - 385), (450 - 430), true, context, "zoneFL50");

		zoneFLTextMonde = new Zone(245, 920, 5, 5, true, context, "zoneFLTextMonde");
	}

	public static Coord putInGameXYValues(Coord coordFoundInZone, Zone searchingZone) {
		if (coordFoundInZone == null || searchingZone == null) {
			return null;
		}
		Coord coordInGame;
		if (coordFoundInZone != null && searchingZone != null) {
			coordInGame = new Coord(coordFoundInZone.getX() + searchingZone.getX(),
					coordFoundInZone.getY() + searchingZone.getY(), coordFoundInZone.getHeight(),
					coordFoundInZone.getWidth(), coordFoundInZone.getName());
			return coordInGame;
		}
		return null;
	}
}
