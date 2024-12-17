package bazmar.lastwar.autofl.data;

import java.io.Serializable;
import java.util.Date;

public class Stats implements Serializable {

	private static final long serialVersionUID = 1L;

	long start = System.currentTimeMillis();

	Date lastFlAction = new Date();

	boolean flInitialized = false;

	int countRecovery = 0, countFL = 0, moyenneFl = 0, countFLAdd = 0, countFLKick = 0, countStrat = 0, countSecu = 0,
			countDev = 0, countScience = 0, countInter = 0, countKickStrat = 0, countKickSecu = 0, countKickDev = 0,
			countKickScience = 0, countKickInter = 0;
	int countMilitary = 0, countAdmin = 0, countKickMilitary = 0, countKickAdmin = 0;
	int countFLTitle = 0;

	long flTime = 0;

	Date nextStratKickCheck = new Date();
	Date nextSecuKickCheck = new Date();
	Date nextDevKickCheck = new Date();
	Date nextScienceKickCheck = new Date();
	Date nextInterKickCheck = new Date();
	Date nextMilitaryKickCheck = new Date();
	Date nextAdminKickCheck = new Date();

	Date startDateStats = new Date();

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public Date getLastFlAction() {
		return lastFlAction;
	}

	public void setLastFlAction(Date lastFlAction) {
		this.lastFlAction = lastFlAction;
	}

	public boolean isFlInitialized() {
		return flInitialized;
	}

	public void setFlInitialized(boolean flInitialized) {
		this.flInitialized = flInitialized;
	}

	public int getCountRecovery() {
		return countRecovery;
	}

	public void setCountRecovery(int countRecovery) {
		this.countRecovery = countRecovery;
	}

	public int getCountFL() {
		return countFL;
	}

	public void setCountFL(int countFL) {
		this.countFL = countFL;
	}

	public int getMoyenneFl() {
		return moyenneFl;
	}

	public void setMoyenneFl(int moyenneFl) {
		this.moyenneFl = moyenneFl;
	}

	public int getCountFLAdd() {
		return countFLAdd;
	}

	public void setCountFLAdd(int countFLAdd) {
		this.countFLAdd = countFLAdd;
	}

	public int getCountFLKick() {
		return countFLKick;
	}

	public void setCountFLKick(int countFLKick) {
		this.countFLKick = countFLKick;
	}

	public int getCountStrat() {
		return countStrat;
	}

	public void setCountStrat(int countStrat) {
		this.countStrat = countStrat;
	}

	public int getCountSecu() {
		return countSecu;
	}

	public void setCountSecu(int countSecu) {
		this.countSecu = countSecu;
	}

	public int getCountDev() {
		return countDev;
	}

	public void setCountDev(int countDev) {
		this.countDev = countDev;
	}

	public int getCountScience() {
		return countScience;
	}

	public void setCountScience(int countScience) {
		this.countScience = countScience;
	}

	public int getCountInter() {
		return countInter;
	}

	public void setCountInter(int countInter) {
		this.countInter = countInter;
	}

	public int getCountKickStrat() {
		return countKickStrat;
	}

	public void setCountKickStrat(int countKickStrat) {
		this.countKickStrat = countKickStrat;
	}

	public int getCountKickSecu() {
		return countKickSecu;
	}

	public void setCountKickSecu(int countKickSecu) {
		this.countKickSecu = countKickSecu;
	}

	public int getCountKickDev() {
		return countKickDev;
	}

	public void setCountKickDev(int countKickDev) {
		this.countKickDev = countKickDev;
	}

	public int getCountKickScience() {
		return countKickScience;
	}

	public void setCountKickScience(int countKickScience) {
		this.countKickScience = countKickScience;
	}

	public int getCountKickInter() {
		return countKickInter;
	}

	public void setCountKickInter(int countKickInter) {
		this.countKickInter = countKickInter;
	}

	public int getCountMilitary() {
		return countMilitary;
	}

	public void setCountMilitary(int countMilitary) {
		this.countMilitary = countMilitary;
	}

	public int getCountAdmin() {
		return countAdmin;
	}

	public void setCountAdmin(int countAdmin) {
		this.countAdmin = countAdmin;
	}

	public int getCountKickMilitary() {
		return countKickMilitary;
	}

	public void setCountKickMilitary(int countKickMilitary) {
		this.countKickMilitary = countKickMilitary;
	}

	public int getCountKickAdmin() {
		return countKickAdmin;
	}

	public void setCountKickAdmin(int countKickAdmin) {
		this.countKickAdmin = countKickAdmin;
	}

	public int getCountFLTitle() {
		return countFLTitle;
	}

	public void setCountFLTitle(int countFLTitle) {
		this.countFLTitle = countFLTitle;
	}

	public long getFlTime() {
		return flTime;
	}

	public void setFlTime(long flTime) {
		this.flTime = flTime;
	}

	public Date getNextStratKickCheck() {
		return nextStratKickCheck;
	}

	public void setNextStratKickCheck(Date nextStratKickCheck) {
		this.nextStratKickCheck = nextStratKickCheck;
	}

	public Date getNextSecuKickCheck() {
		return nextSecuKickCheck;
	}

	public void setNextSecuKickCheck(Date nextSecuKickCheck) {
		this.nextSecuKickCheck = nextSecuKickCheck;
	}

	public Date getNextDevKickCheck() {
		return nextDevKickCheck;
	}

	public void setNextDevKickCheck(Date nextDevKickCheck) {
		this.nextDevKickCheck = nextDevKickCheck;
	}

	public Date getNextScienceKickCheck() {
		return nextScienceKickCheck;
	}

	public void setNextScienceKickCheck(Date nextScienceKickCheck) {
		this.nextScienceKickCheck = nextScienceKickCheck;
	}

	public Date getNextInterKickCheck() {
		return nextInterKickCheck;
	}

	public void setNextInterKickCheck(Date nextInterKickCheck) {
		this.nextInterKickCheck = nextInterKickCheck;
	}

	public Date getNextMilitaryKickCheck() {
		return nextMilitaryKickCheck;
	}

	public void setNextMilitaryKickCheck(Date nextMilitaryKickCheck) {
		this.nextMilitaryKickCheck = nextMilitaryKickCheck;
	}

	public Date getNextAdminKickCheck() {
		return nextAdminKickCheck;
	}

	public void setNextAdminKickCheck(Date nextAdminKickCheck) {
		this.nextAdminKickCheck = nextAdminKickCheck;
	}

	public Date getStartDateStats() {
		return startDateStats;
	}

	public void setStartDateStats(Date startDateStats) {
		this.startDateStats = startDateStats;
	}

	@Override
	public String toString() {
		return "Stats [start=" + start + ", lastFlAction=" + lastFlAction + ", flInitialized=" + flInitialized
				+ ", countRecovery=" + countRecovery + ", countFL=" + countFL + ", moyenneFl=" + moyenneFl
				+ ", countFLAdd=" + countFLAdd + ", countFLKick=" + countFLKick + ", countStrat=" + countStrat
				+ ", countSecu=" + countSecu + ", countDev=" + countDev + ", countScience=" + countScience
				+ ", countInter=" + countInter + ", countKickStrat=" + countKickStrat + ", countKickSecu="
				+ countKickSecu + ", countKickDev=" + countKickDev + ", countKickScience=" + countKickScience
				+ ", countKickInter=" + countKickInter + ", countMilitary=" + countMilitary + ", countAdmin="
				+ countAdmin + ", countKickMilitary=" + countKickMilitary + ", countKickAdmin=" + countKickAdmin
				+ ", countFLTitle=" + countFLTitle + ", flTime=" + flTime + ", nextStratKickCheck=" + nextStratKickCheck
				+ ", nextSecuKickCheck=" + nextSecuKickCheck + ", nextDevKickCheck=" + nextDevKickCheck
				+ ", nextScienceKickCheck=" + nextScienceKickCheck + ", nextInterKickCheck=" + nextInterKickCheck
				+ ", nextMilitaryKickCheck=" + nextMilitaryKickCheck + ", nextAdminKickCheck=" + nextAdminKickCheck
				+ ", startDateStats=" + startDateStats + "]";
	}

}
