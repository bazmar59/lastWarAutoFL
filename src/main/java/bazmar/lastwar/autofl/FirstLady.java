package bazmar.lastwar.autofl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;

import bazmar.lastwar.autofl.data.BotType;
import bazmar.lastwar.autofl.data.BuffNumber;
import bazmar.lastwar.autofl.data.Coord;
import bazmar.lastwar.autofl.data.Images;
import bazmar.lastwar.autofl.data.Stats;
import bazmar.lastwar.autofl.data.Zone;
import bazmar.lastwar.autofl.data.Zones;
import bazmar.lastwar.autofl.image.Image;
import bazmar.lastwar.autofl.image.ImageReco;
import bazmar.lastwar.autofl.io.Frame;
import bazmar.lastwar.autofl.io.Mouse;
import bazmar.lastwar.autofl.io.Screen;
import bazmar.lastwar.autofl.utils.ProcessManager;
import bazmar.lastwar.autofl.utils.Utils;
import ch.qos.logback.classic.Logger;

public class FirstLady {

	private static Logger logger = (Logger) LoggerFactory.getLogger("FL");

	private Mouse mouseFl;
	private Screen screenFl;
	private static BuffNumber buffNumber = null;

	public FirstLady(Mouse mouseFl, Screen screenFl) {
		this.mouseFl = mouseFl;
		this.screenFl = screenFl;
	}

	public void firstLadySingleRoutine(Stats stats) {
		Frame.updateCurrentBot(BotType.FL);
		long start = System.currentTimeMillis();

		if (buffNumber == null) {
			buffNumber = BuffNumber.SIX;
			initFL(stats);
		}

		List<Zone> flZones = new ArrayList<Zone>();
		if (buffNumber.equals(BuffNumber.SIX)) {
			flZones.add(Zones.zoneFLStrat);
			flZones.add(Zones.zoneFLSecu);
			flZones.add(Zones.zoneFLDev);
			flZones.add(Zones.zoneFLScience);
			flZones.add(Zones.zoneFLInter);
		}
		if (buffNumber.equals(BuffNumber.EIGHT)) {
			flZones.add(Zones.zoneFLStrat8);
			flZones.add(Zones.zoneFLSecu8);
			flZones.add(Zones.zoneFLDev8);
			flZones.add(Zones.zoneFLScience8);
			flZones.add(Zones.zoneFLInter8);
		}
		if (buffNumber.equals(BuffNumber.EIGHT_WIN)) {
			flZones.add(Zones.zoneFLMilitaire8w);
			flZones.add(Zones.zoneFLAdmin8w);
			flZones.add(Zones.zoneFLStrat8w);
			flZones.add(Zones.zoneFLSecu8w);
			flZones.add(Zones.zoneFLDev8w);
			flZones.add(Zones.zoneFLScience8w);
			flZones.add(Zones.zoneFLInter8w);
		}

		for (Zone currentZone : flZones) {
			logger.info("CURRENT ZONE %s".formatted(currentZone.getName()));
			initFL(stats);

			acceptIfNeeded(currentZone, stats);

			kickIfNeeded(currentZone, stats);

			Utils.updateLogs();
		}
		stats.setCountFL(stats.getCountFL() + 1);

		if (stats.getCountFL() % 20 == 0) {
			clickReturnBlueStack();
			Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
			clickReturnBlueStack();
			Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);

		}

		long iterTime = System.currentTimeMillis() - start;
		stats.setFlTime(stats.getFlTime() + iterTime);
		stats.setMoyenneFl((int) (stats.getFlTime() / stats.getCountFL()));
		Frame.updateFrameStats(stats);
	}

	private void acceptIfNeeded(Zone currentZone, Stats stats) {

		Image notif = screenFl.screenMemory(currentZone);
		if (ImageReco.findFirst(notif, Images.imgFLNotif) != null) {
			logger.info("Notif found for %s".formatted(currentZone.getName()));
			mouseFl.clickCenter(currentZone);

			if (waitFlListWhenAvailable()) {
				if (ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFL50), Images.imgFL50) != null) {
					clickReturnBlueStack();
					Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
					return;
				} else {
					mouseFl.clickCenter(Zones.zoneFLList);
				}

			}

			Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);

			// Check really button accept only for stats
			checkAndUpdateStats(currentZone, stats);
			clickFLAccept(stats);
			Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
			mouseFl.clickCenter(Zones.zoneFLClose);
			Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
			mouseFl.clickCenter(Zones.zoneFLClose);
			Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
		} else {
			logger.debug("Notif not found for %s".formatted(currentZone.getName()));
		}
	}

	private boolean waitFlListWhenAvailable() {
		Utils.pause(500);
		int iter = 0;
		while (ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLList), Images.imgFLListExist) == null) {
			Utils.pause(100);
			iter++;
			if (iter > 50) {
				recoveryIfListNotExist();
				return false;
			}
		}
		if (iter < 50) {
			Utils.pause(500);
			return true;
		}
		return false;
	}

	private void kickIfNeeded(Zone currentZone, Stats stats) {

		boolean check = false;
		switch (currentZone.getName()) {
		case "zoneFLStrat":
		case "zoneFLStrat8":
		case "zoneFLStrat8w":
			if (stats.getNextStratKickCheck().before(new Date())) {
				stats.setNextStratKickCheck(Utils.getNextMinute(1));
				check = true;
				logger.info("Next strat Kick check =%s".formatted(stats.getNextStratKickCheck()));
			}
			break;
		case "zoneFLSecu":
		case "zoneFLSecu8":
		case "zoneFLSecu8w":
			if (stats.getNextSecuKickCheck().before(new Date())) {
				stats.setNextSecuKickCheck(Utils.getNextMinute(1));
				check = true;
				logger.info("Next secu Kick check =%s".formatted(stats.getNextSecuKickCheck()));
			}
			break;
		case "zoneFLDev":
		case "zoneFLDev8":
		case "zoneFLDev8w":
			if (stats.getNextDevKickCheck().before(new Date())) {
				stats.setNextDevKickCheck(Utils.getNextMinute(1));
				check = true;
				logger.info("Next dev Kick check =%s".formatted(stats.getNextDevKickCheck()));
			}
			break;
		case "zoneFLScience":
		case "zoneFLScience8":
		case "zoneFLScience8w":
			if (stats.getNextScienceKickCheck().before(new Date())) {
				stats.setNextScienceKickCheck(Utils.getNextMinute(1));
				check = true;
				logger.info("Next science Kick check =%s".formatted(stats.getNextScienceKickCheck()));
			}
			break;
		case "zoneFLInter":
		case "zoneFLInter8":
		case "zoneFLInter8w":
			if (stats.getNextInterKickCheck().before(new Date())) {
				stats.setNextInterKickCheck(Utils.getNextMinute(1));
				check = true;
				logger.info("Next inter Kick check =%s".formatted(stats.getNextInterKickCheck()));
			}
			break;
		case "zoneFLMilitaire8w":
			if (stats.getNextMilitaryKickCheck().before(new Date())) {
				stats.setNextMilitaryKickCheck(Utils.getNextMinute(1));
				check = true;
				logger.info("Next military Kick check =%s".formatted(stats.getNextMilitaryKickCheck()));
			}
			break;
		case "zoneFLAdmin8w":
			if (stats.getNextAdminKickCheck().before(new Date())) {
				stats.setNextAdminKickCheck(Utils.getNextMinute(1));
				check = true;
				logger.info("Next Admin Kick check =%s".formatted(stats.getNextAdminKickCheck()));
			}
			break;
		default:
			logger.warn("%s not managed for kickIfNeeded".formatted(currentZone.getName()));
		}

		if (check) {
			boolean kickInTitle = false;
			boolean kickInDetail = false;
			Image checkKickInTitle = null;
			Image checkKickInTitleTime = null;
			Image checkKickInDetail = null;

			// Retrieve time in title
			checkKickInTitleTime = screenFl.screenMemory(findTimeFor(currentZone), false,
					"checkKickInTitleTime%s".formatted(currentZone.getName()));

			// Check title isn't vacant
			if (ImageReco.findFirst(checkKickInTitleTime,
					Arrays.asList(Images.imgFLVacant, Images.imgFLVacant2)) != null) {
				logger.info("%s VACANT: No need to kick".formatted(currentZone.getName()));
				addTimeToKickStats(currentZone, stats, 10);
				return;
			}

			// Check that people isn't block
			checkKickInTitle = screenFl.screenMemory(currentZone, false,
					"checkKickInTitle%s".formatted(currentZone.getName()));

			if (!find000InTitle(checkKickInTitleTime)) {
				logger.info("Kick in title %s".formatted(currentZone.getName()));
				kickInTitle = true;
			}

			if (kickInTitle) {
				mouseFl.clickCenter(currentZone);
				Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);

				// Check that people isn't block
				checkKickInDetail = screenFl.screenMemory(Zones.zoneFLTime, false,
						"checkKickInDetail%s".formatted(currentZone.getName()));

				if (!find000InDetail(checkKickInDetail)) {
					logger.info("Kick in detail %s".formatted(currentZone.getName()));
					kickInDetail = true;
				}
			}

			// kick process
			if (kickInDetail && kickInTitle) {
				Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
				if (ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLReject), Images.imgFLReject) != null) {

					logger.info("Kick some guy in %s".formatted(currentZone.getName()));
					screenFl.saveKick(checkKickInTitle, "%s-kickTitleTime".formatted(currentZone.getName()));
					screenFl.saveKick(checkKickInDetail, "%s-kickDetailTime".formatted(currentZone.getName()));

					mouseFl.clickCenter(Zones.zoneFLReject);
					Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
					mouseFl.clickCenter(Zones.zoneFLConfirmReject);
					Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
					mouseFl.clickCenter(Zones.zoneFLClose);
					Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);

					switch (currentZone.getName()) {
					case "zoneFLStrat":
					case "zoneFLStrat8":
					case "zoneFLStrat8w":
						stats.setCountKickStrat(stats.getCountKickStrat() + 1);
						break;
					case "zoneFLSecu":
					case "zoneFLSecu8":
					case "zoneFLSecu8w":
						stats.setCountKickSecu(stats.getCountKickSecu() + 1);
						break;
					case "zoneFLDev":
					case "zoneFLDev8":
					case "zoneFLDev8w":
						stats.setCountKickDev(stats.getCountKickDev() + 1);
						break;
					case "zoneFLScience":
					case "zoneFLScience8":
					case "zoneFLScience8w":
						stats.setCountKickScience(stats.getCountKickScience() + 1);
						break;
					case "zoneFLInter":
					case "zoneFLInter8":
					case "zoneFLInter8w":
						stats.setCountKickInter(stats.getCountKickInter() + 1);
						break;
					default:
						logger.warn("%s not managed for kick stats".formatted(currentZone.getName()));
					}

					stats.setLastFlAction(new Date());
					stats.setCountFLKick(stats.getCountFLKick() + 1);
					Frame.updateFrameStats(stats);

				} else {
					logger.info("Nobody to reject");

					if (recoveryIfListNotExist()) {
						stats.setCountRecovery(stats.getCountRecovery() + 1);
						return;
					}

					mouseFl.clickCenter(Zones.zoneFLClose);
					Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
				}
			} else {
				logger.info("No Kick kickInDetail=%s kickInTitle=%s".formatted(kickInDetail, kickInTitle));
			}
		} else {
			logger.debug("No Kick check needed for %s".formatted(currentZone.getName()));
		}

	}

	private void addTimeToKickStats(Zone currentZone, Stats stats, int minutesToAdd) {
		switch (currentZone.getName()) {
		case "zoneFLStrat":
		case "zoneFLStrat8":
		case "zoneFLStrat8w":
			stats.setNextStratKickCheck(Utils.getNextMinute(minutesToAdd));
			logger.info("Next strat Kick check =%s".formatted(stats.getNextStratKickCheck()));
			break;
		case "zoneFLSecu":
		case "zoneFLSecu8":
		case "zoneFLSecu8w":
			stats.setNextSecuKickCheck(Utils.getNextMinute(minutesToAdd));
			logger.info("Next secu Kick check =%s".formatted(stats.getNextSecuKickCheck()));
			break;
		case "zoneFLDev":
		case "zoneFLDev8":
		case "zoneFLDev8w":
			stats.setNextDevKickCheck(Utils.getNextMinute(minutesToAdd));
			logger.info("Next dev Kick check =%s".formatted(stats.getNextDevKickCheck()));
			break;
		case "zoneFLScience":
		case "zoneFLScience8":
		case "zoneFLScience8w":
			stats.setNextScienceKickCheck(Utils.getNextMinute(minutesToAdd));
			logger.info("Next science Kick check =%s".formatted(stats.getNextScienceKickCheck()));
			break;
		case "zoneFLInter":
		case "zoneFLInter8":
		case "zoneFLInter8w":
			stats.setNextInterKickCheck(Utils.getNextMinute(minutesToAdd));
			logger.info("Next inter Kick check =%s".formatted(stats.getNextInterKickCheck()));
			break;
		case "zoneFLMilitaire8w":
			stats.setNextMilitaryKickCheck(Utils.getNextMinute(minutesToAdd));
			logger.info("Next military Kick check =%s".formatted(stats.getNextMilitaryKickCheck()));
			break;
		case "zoneFLAdmin8w":
			stats.setNextAdminKickCheck(Utils.getNextMinute(minutesToAdd));
			logger.info("Next admin Kick check =%s".formatted(stats.getNextAdminKickCheck()));
			break;
		default:
			logger.warn("%s not managed for addTimeToKickChercherStats".formatted(currentZone.getName()));
		}
		Frame.updateFrameStats(stats);
	}

	private Zone findTimeFor(Zone currentZone) {
		switch (currentZone.getName()) {
		case "zoneFLStrat":
			return Zones.zoneFLStratTime;
		case "zoneFLSecu":
			return Zones.zoneFLSecuTime;
		case "zoneFLDev":
			return Zones.zoneFLDevTime;
		case "zoneFLScience":
			return Zones.zoneFLScienceTime;
		case "zoneFLInter":
			return Zones.zoneFLInterTime;
		case "zoneFLStrat8":
			return Zones.zoneFLStrat8Time;
		case "zoneFLSecu8":
			return Zones.zoneFLSecu8Time;
		case "zoneFLDev8":
			return Zones.zoneFLDev8Time;
		case "zoneFLScience8":
			return Zones.zoneFLScience8Time;
		case "zoneFLInter8":
			return Zones.zoneFLInter8Time;
		case "zoneFLStrat8w":
			return Zones.zoneFLStrat8wTime;
		case "zoneFLSecu8w":
			return Zones.zoneFLSecu8wTime;
		case "zoneFLDev8w":
			return Zones.zoneFLDev8wTime;
		case "zoneFLScience8w":
			return Zones.zoneFLScience8wTime;
		case "zoneFLInter8w":
			return Zones.zoneFLInter8wTime;
		case "zoneFLMilitaire8w":
			return Zones.zoneFLMilitaire8wTime;
		case "zoneFLAdmin8w":
			return Zones.zoneFLAdmin8wTime;
		default:
			logger.warn("%s not managed for findTimeFor".formatted(currentZone.getName()));
		}
		return null;
	}

	private void initFL(Stats stats) {
		while (!weAreInBuffPage()) {

			logger.error("Unable to find %s with %s".formatted(Images.imgFLTitle, buffNumber));

			if (LastWarMain.PAUSE) {
				Utils.pause(1000);
			}

			Coord returnBlueStack = null;
			while (returnBlueStack == null) {
				logger.warn("Unable to find returnBluestack");
				returnBlueStack = findReturnBluestack();
				if (returnBlueStack == null) {
					logger.info("RECOVERY PLAN ReturnBluestack not found Wait 60s for restart");
					screenFl.screenMemory(0, 0, 1920, 1080, true, "RECOVERY_FL_NO_RETURN_BLUSTACK");
					stats.setCountRecovery(stats.getCountRecovery() + 1);
					ProcessManager.killBluestackX();
					Utils.pause(60000);
					LastWarMain.reloadContexts();
				}
				if (LastWarMain.PAUSE) {
					Utils.pause(1000);
				}
			}

			int findWorldOrBaseTry = 0;
			while (findWorldCoord() == null && findBaseCoord() == null) {
				logger.warn("Unable to find world or base after %s try".formatted(findWorldOrBaseTry));
				clickReturnBlueStack();
				Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);

				findWorldOrBaseTry++;
				// Maybe a big menu => Game not yet started
				if (findWorldOrBaseTry > 10 && ImageReco.findFirst(screenFl.screenMemory(0, 0, 1920, 40, true),
						Images.imgBigBluestackMenu, 10) != null) {
					logger.info("RECOVERY PLAN Big menu found wait 60s for game restart");
					screenFl.screenMemory(0, 0, 1920, 1080, true,
							"RECOVERY_FL_UP10_ITER_%s".formatted(findWorldOrBaseTry));
					ProcessManager.killBluestackX();
					stats.setCountRecovery(stats.getCountRecovery() + 1);
					Utils.pause(60000);
					LastWarMain.reloadContexts();
					findWorldOrBaseTry = 0;
				}

				if (findWorldOrBaseTry > 20) {
					logger.info("RECOVERY PLAN Click reconnect if exists and wait 60s");
					screenFl.screenMemory(0, 0, 1920, 1080, true,
							"RECOVERY_FL_UP20_ITER_%s".formatted(findWorldOrBaseTry));
					stats.setCountRecovery(stats.getCountRecovery() + 1);
					mouseFl.clickCenter(
							ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLGame), Images.imgButtonBlue));
					Utils.pause(60000);
					LastWarMain.reloadContexts();
					findWorldOrBaseTry = 0;
				}

				if (findWorldOrBaseTry > 30) {
					logger.info("RECOVERY PLAN Click reconnect if exists and wait 60s");
					screenFl.screenMemory(0, 0, 1920, 1080, true,
							"RECOVERY_FL_UP30_ITER_%s".formatted(findWorldOrBaseTry));
					stats.setCountRecovery(stats.getCountRecovery() + 1);
					ProcessManager.killBluestackX();
					Utils.pause(60000);
					LastWarMain.reloadContexts();
					findWorldOrBaseTry = 0;
				}

				if (findWorldOrBaseTry > 40) {
					logger.info("RECOVERY PLAN BREAK");
					screenFl.screenMemory(0, 0, 1920, 1080, true,
							"RECOVERY_FL_BREAK_ITER_%s".formatted(findWorldOrBaseTry));
					break;
				}
				if (LastWarMain.PAUSE) {
					Utils.pause(1000);
				}
			}
			mouseFl.clickCenter(Zones.zoneFLAvatar);
			Utils.pause(2 * LastWarMain.PAUSE_BETWEEN_FL_ACTION);
			mouseFl.clickCenter(Zones.zoneFL253);
			Utils.pause(2 * LastWarMain.PAUSE_BETWEEN_FL_ACTION);

			// check loose
			if (ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLConquerant), Images.imgFLConquerant,
					10) != null) {
				logger.info("Mode 8 buffs");
				buffNumber = BuffNumber.EIGHT;
				mouseFl.drag(0, -200);
				Utils.pause(2 * LastWarMain.PAUSE_BETWEEN_FL_ACTION);
			}

			// check win
			if (ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLCommandantMilitaireWin),
					Images.imgFLCommandantMilitaire, 10) != null) {
				logger.info("Mode 8 win buffs");
				buffNumber = BuffNumber.EIGHT_WIN;
				mouseFl.drag(0, -200);
				Utils.pause(2 * LastWarMain.PAUSE_BETWEEN_FL_ACTION);
			}
		}

	}

	private boolean weAreInBuffPage() {
		if (buffNumber.equals(BuffNumber.SIX)
				&& ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLStrat), Images.imgFLTitle) != null) {
			return true;
		}
		if (buffNumber.equals(BuffNumber.EIGHT)
				&& ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLStrat8), Images.imgFLTitle8) != null) {
			return true;
		}
		if (buffNumber.equals(BuffNumber.EIGHT_WIN)
				&& ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLStrat8w), Images.imgFLTitle8) != null) {
			return true;
		}

		return false;
	}

	private void clickFLAccept(Stats stats) {
		if (ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLAccept), Images.imgFLFullList) != null) {
			logger.info("List Full -> Drag");
			mouseFl.drag(0, 300, true);
			Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
			mouseFl.drag(0, 300, true);
			Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
			mouseFl.drag(0, 300, true);
			Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
		}
		mouseFl.clickCenter(Zones.zoneFLAccept);
		stats.setLastFlAction(new Date());
	}

	private boolean find000InDetail(Image checkKickInDetail) {
		if (ImageReco.findFirst(checkKickInDetail, Arrays.asList(Images.imgFLZero, Images.imgFLZero2)) == null) {
			return false;
		}
		return true;
	}

	private boolean find000InTitle(Image checkKickInTitle) {
		if (ImageReco.findFirst(checkKickInTitle,
				Arrays.asList(Images.imgFLZeroWhite, Images.imgFLZeroWhite2, Images.imgFLZeroWhite3)) == null) {
			return false;
		}
		return true;
	}

	private void checkAndUpdateStats(Zone currentZone, Stats stats) {
		if (ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLAccept), Images.imgFLAccept, 10) != null) {
			switch (currentZone.getName()) {
			case "zoneFLStrat":
			case "zoneFLStrat8":
			case "zoneFLStrat8w":
				stats.setCountStrat(stats.getCountStrat() + 1);
				stats.setNextStratKickCheck(Utils.getNextMinute(10));
				break;
			case "zoneFLSecu":
			case "zoneFLSecu8":
			case "zoneFLSecu8w":
				stats.setCountSecu(stats.getCountSecu() + 1);
				stats.setNextSecuKickCheck(Utils.getNextMinute(10));
				break;
			case "zoneFLDev":
			case "zoneFLDev8":
			case "zoneFLDev8w":
				stats.setCountDev(stats.getCountDev() + 1);
				stats.setNextDevKickCheck(Utils.getNextMinute(10));
				break;
			case "zoneFLScience":
			case "zoneFLScience8":
			case "zoneFLScience8w":
				stats.setCountScience(stats.getCountScience() + 1);
				stats.setNextScienceKickCheck(Utils.getNextMinute(10));
				break;
			case "zoneFLInter":
			case "zoneFLInter8":
			case "zoneFLInter8w":
				stats.setCountInter(stats.getCountInter() + 1);
				stats.setNextInterKickCheck(Utils.getNextMinute(10));
				break;
			case "zoneFLMilitaire8w":
				stats.setCountMilitary(stats.getCountMilitary() + 1);
				stats.setNextMilitaryKickCheck(Utils.getNextMinute(10));
				break;
			case "zoneFLAdmin8w":
				stats.setCountAdmin(stats.getCountAdmin() + 1);
				stats.setNextAdminKickCheck(Utils.getNextMinute(10));
				break;
			default:
				logger.warn("%s not managed for accept stats".formatted(currentZone.getName()));
				break;
			}
			stats.setCountFLAdd(stats.getCountFLAdd() + 1);
			Frame.updateFrameStats(stats);
		}

	}

	private boolean recoveryIfListNotExist() {
		int maxAttempts = 5;
		for (int attempt = 0; attempt < maxAttempts; attempt++) {
			if (ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLList), Images.imgFLListExist, 10) != null) {
				return false;
			}
			if (attempt > 2) {
				screenFl.screenMemory(Zones.zoneFLList, true, "RECOVERY_LIST_KO_ITER_%s".formatted(attempt));
			}
			Utils.pause(LastWarMain.PAUSE_BETWEEN_FL_ACTION);
		}

		logger.info("RECOVERY PLAN List not exist (Connected by phone recently ?) wait 60s for game restart");
		screenFl.screenMemory(Zones.zoneFLList, true, "RECOVERY_LIST_KO");
		ProcessManager.killBluestackX();
		Utils.pause(60000);
		LastWarMain.reloadContexts();
		return true;
	}

	private void clickReturnBlueStack() {
		mouseFl.clickCenterWithoutOrigin(findReturnBluestack());
	}

	private Coord findReturnBluestack() {
		Coord returnBlueStack = ImageReco.findFirst(
				screenFl.screenMemory(screenFl.getOrigX(), 0, screenFl.getWidth(), 40, false),
				Arrays.asList(Images.imgReturnBluestatck, Images.imgReturnBluestatckHoover), 10);
		if (returnBlueStack != null) {
			returnBlueStack.setX(returnBlueStack.getX() + screenFl.getOrigX());
		}
		return returnBlueStack;
	}

	private Coord findWorldCoord() {
		Coord worldCoord = ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLBaseOrWorld), Images.imgWorld, 10);
		if (worldCoord != null) {
			worldCoord = Zones.putInGameXYValues(worldCoord, Zones.zoneFLBaseOrWorld);
		} else {
			logger.warn("[findWorldCoord] World not found");
		}
		return worldCoord;

	}

	private Coord findBaseCoord() {
		Coord baseCoord = ImageReco.findFirst(screenFl.screenMemory(Zones.zoneFLBaseOrWorld),
				Arrays.asList(Images.imgBaseZoomed, Images.imgBaseNotZoomed), 10);
		if (baseCoord != null) {
			baseCoord = Zones.putInGameXYValues(baseCoord, Zones.zoneFLBaseOrWorld);
		} else {
			logger.warn("[findBaseCoord] Base not found");
		}
		return baseCoord;
	}

	public Mouse getMouseFl() {
		return mouseFl;
	}

	public void setMouseFl(Mouse mouseFl) {
		this.mouseFl = mouseFl;
	}

	public Screen getScreenFl() {
		return screenFl;
	}

	public void setScreenFl(Screen screenFl) {
		this.screenFl = screenFl;
	}

	@Override
	public String toString() {
		return "FirstLady [mouseFl=" + mouseFl + ", screenFl=" + screenFl + "]";
	}

}