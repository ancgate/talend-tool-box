package fr.evandycke.talend.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import fr.evandycke.talend.numeric.IntegerHelper;
import fr.evandycke.talend.string.StringHelper;

/**
 * DateHelper est la classe des outils de manipulation des objets de type Date.
 * 
 * @author elie.vandycke
 * 
 */
public final class DateHelper {

	/**
	 * Chaine vide
	 */
	private static final String EMPTY_STRING = "";

	/**
	 * Format de date par d�faut
	 */
	private static final String DEFAULT_PATTERN = "dd/MM/yyyy";

	/**
	 * Format de date Long
	 */
	private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Tableau des principaux formats de date utilis�s
	 */
	private static final String [] FORMAT_DATE = {

		//en format tiret
		"yyyy-MM-dd",
		//en format j/m/a
		"M/d/yy",
		"M/dd/yy",
		"MM/dd/yy",
		//en format j/m/a h:m
		"M/d/yy H:mm",
		"M/dd/yy HH:mm",
		"M/d/yy HH:mm",
		"MM/dd/yy H:mm",
		//en format j/m/a h:m:s
		"yyyy/MM/dd HH:mm:ss.SSS",
		"yyyy-MM-dd HH:mm:ss.SSS",
		"yyyy-MM-dd HH:mm:ss.SS",
		"yyyy-MM-dd HH:mm:ss.S",
		"yyyy-MM-dd H:mm:ss",
		//en format date courte 
		DEFAULT_PATTERN

	};


	/**
	 * D�termine si la valeur sp�cifi�e est post�rieure � la valeur de r�f�rence
	 * 
	 * @param value
	 *            Valeur
	 * @param refValue
	 *            Valeur de r�f�rence
	 * @return Vrai si la valeur sp�cifi�e est post�rieure � la valeur de
	 *         r�f�rence ; Sinon Faux
	 */
	public static boolean after(final Object value, final Object refValue) {

		final Date dateValue = tryParse(value, DateHelper.getCurrentDate());
		final Date dateRefValue = tryParse(refValue,
				DateHelper.getCurrentDate());

		return dateValue.after(dateRefValue);
	}

	/**
	 * D�termine si la valeur sp�cifi�e est ant�rieure � la valeur de r�f�rence
	 * 
	 * @param value
	 *            Valeur
	 * @param refValue
	 *            Valeur de r�f�rence
	 * @return Vrai si la valeur sp�cifi�e est ant�rieure � la valeur de
	 *         r�f�rence ; Sinon Faux
	 */
	public static boolean before(final Object value, final Object refValue) {

		final Date dateValue = tryParse(value, DateHelper.getCurrentDate());
		final Date dateRefValue = tryParse(refValue,
				DateHelper.getCurrentDate());

		return dateValue.before(dateRefValue);
	}

	/**
	 * Permet de tester si une date est comprise entre deux autres (bornes inclues ou exclues)
	 * @param date
	 * @param dateMin
	 * @param dateMax
	 * @param bornesInclues
	 * @return true si compris entre. False dans les autres cas.
	 */
	public static boolean between (Date date, Date dateMin, Date dateMax, boolean bornesInclues){
		boolean rslt = false;
		if(!isNull(date) && !isNull(dateMin) && !isNull(dateMax)){
			//si on inclue les bornes
			if(bornesInclues && (dateMin.before(date) || date.equals(dateMin)) && (dateMax.after(date) || dateMax.equals(date))){
				rslt = true;
			}
			//si les bornes ne sont pas inclues
			else if(dateMin.before(date) && dateMax.after(date)){
					rslt = true;
			}
		}
		return rslt;
	}

	/**
	 * Formate la date sp�cifi�e au format sp�cifi�
	 * 
	 * @param value
	 *            Date
	 * @param format
	 *            Format
	 * @return Date format�e
	 */
	public static String formatDate(final String format, final Date value) {

		final String defaultValue = DateHelper.EMPTY_STRING;

		if (value == null) {
			return defaultValue;
		}

		if (StringHelper.isNullOrEmptyOrSpace(format)) {
			return defaultValue;
		}

		try {
			final DateFormat dateFormat = new SimpleDateFormat(format,
					Locale.FRANCE);
			return dateFormat.format(value);
		} catch (IllegalArgumentException e) {
			return defaultValue;
		}
	}

	/**
	 * Formate la date sp�cifi�e en une repr�sentIllegalArgumentExceptionation
	 * complete jour date mois ann�e heure minute seconde fuseau horaire
	 * 
	 * @param value
	 *            Date
	 * @return Date format�e
	 */
	public static String formatDateComplete(final Date value) {
		return formatDate(DATE_TIME_PATTERN, value);
	}

	/**
	 * Formate la date sp�cifi�e en une repr�sentation courte JJ/MM/AAAA
	 * 
	 * @param value
	 *            Date
	 * @return Date format�e
	 */
	public static String formatDateCourt(final Date value) {

		return isNull(value) ? null : formatDate(DEFAULT_PATTERN, value);
	}

	/**
	 * Formate la date sp�cifi�e en une repr�sentation longue jour date mois
	 * ann�e
	 * 
	 * @param value
	 *            Date
	 * @return Date format�e
	 */
	public static String formatDateLong(final Date value) {
		return formatDate("EEEE d MMM yyyy", value);
	}

	/**
	 * Obtient la date du jour
	 * 
	 * @return Date du jour
	 */
	public static Date getCurrentDate() {

		final Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Obtient la date et l'heure du jour
	 * 
	 * @return Date et heure du jour
	 */
	public static Date getCurrentDateTime() {

		return Calendar.getInstance(Locale.FRANCE).getTime();
	}

	/**
	 * Obtient une date en fonction des valeurs sp�cifi�es
	 * 
	 * @param year
	 *            Ann�e
	 * @param month
	 *            Mois (base 1)
	 * @param day
	 *            Jour
	 * @return Date
	 */
	public static Date getDate(final int year, final int month, final int day) {

		final Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Obtient une date/heure en fonction des valeurs sp�cifi�es
	 * 
	 * @param year
	 *            Ann�e
	 * @param month
	 *            Mois (base 1)
	 * @param day
	 *            Jour
	 * @param hour
	 *            Heures
	 * @param minutes
	 *            Minutes
	 * @param seconds
	 *            Secondes
	 * @return Date
	 */
	public static Date getDateTime(final int year, final int month,
			final int day, final int hour, final int minutes, final int seconds) {

		final Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Obtient la date et l'heure courante au format plat
	 * 
	 * @return Date et l'heure courante au format plat
	 */
	public static String getFlatCurrentDateTime() {

		return getFlatCurrentDateTime(getCurrentDateTime());
	}

	/**
	 * Obtient la date et l'heure courante au format plat
	 * 
	 * @return Date et l'heure courante au format plat
	 */
	public static String getFlatCurrentDateTime(final Date value) {

		if (value == null) {
			return EMPTY_STRING;
		}

		final Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.setTime(value);

		final StringBuilder strBld = new StringBuilder(6);
		return strBld
				.append(StringHelper.addLeadingZero(4, cal.get(Calendar.YEAR)))
				.append(StringHelper.addLeadingZero(2,
						cal.get(Calendar.MONTH) + 1))
						.append(StringHelper.addLeadingZero(2,
								cal.get(Calendar.DAY_OF_MONTH)))
								.append(StringHelper.addLeadingZero(2,
										cal.get(Calendar.HOUR_OF_DAY)))
										.append(StringHelper.addLeadingZero(2, cal.get(Calendar.MINUTE)))
										.append(StringHelper.addLeadingZero(2, cal.get(Calendar.SECOND)))
										.toString();
	}
	/**
	 * Permet de tester si une String (avec un pattern donn�) est une date
	 * @param stringDate
	 * @param pattern
	 * @return true s'il s'agit d'une date, sinon false
	 */
	public static boolean isDate(String stringDate, String pattern) {

		Date testDate = null;

		try {
			testDate = parse(pattern, stringDate);
		} catch (ParseException e) {
			return false;
		}

		String formatDate = formatDate(pattern, testDate);
		if(formatDate.equalsIgnoreCase(stringDate) || formatDate.length()== stringDate.length()) {

			return true;
		}

		return false;

	}

	/**
	 * D�termine si la valeur sp�cifi�e est nulle ou non
	 * 
	 * @param value
	 *            Valeur � tester
	 * @return Vrai si la valeur est nulle ; Sinon Faux
	 */
	public static boolean isNull(final Date value) {

		return value == null;
	}

	/**
	 * Convertit l'objet sp�cifi� en objet de type Date
	 * 
	 * @param format
	 *            Mod�le de date
	 * @param value
	 *            Valeur � convertir
	 * @return Valeur convertie
	 * @throws ParseException
	 */
	private static Date parse(final String format, final Object value)
			throws ParseException {

		if (value == null) {
			return null;
		}

		if (value instanceof Date) {
			return (Date) value;
		}

		String pattern = format;

		if (StringHelper.isNullOrEmptyOrSpace(format)) {
			pattern = DEFAULT_PATTERN;
		}

		final String strValue = String.valueOf(value);
		final SimpleDateFormat sdf = new SimpleDateFormat(pattern,
				Locale.FRANCE);
		sdf.setLenient(false);
		return sdf.parse(strValue);
	}

	/**
	 * Retourne une date al�atoire, comprise entre les valeurs sp�cifi�es
	 * 
	 * @param min
	 *            Ann�e minimum
	 * @param max
	 *            Ann�e maximum
	 * @return Valeur al�atoire (y compris min/max)
	 */
	public static Date random(final int min, final int max) {

		final GregorianCalendar cal = new GregorianCalendar();

		// Une ann�e au hasard
		final int annee = IntegerHelper.random(min, max);
		cal.set(GregorianCalendar.YEAR, annee);

		// Un jour dans l'ann�e au hasard
		final int jourAnnee = IntegerHelper.random(1,
				GregorianCalendar.DAY_OF_YEAR);
		cal.set(GregorianCalendar.DAY_OF_YEAR, jourAnnee);

		return cal.getTime();
	}

	/**
	 * Permet de transformer la date en parametre en une date au bon format.
	 * @param dateATransformer en String
	 * @return la date au format dd/MM/yyyy ou "Format non g�r�"
	 */
	public static String transformDate(final String dateATransformer){
		//valeur par d�faut retourn�e
		String resultat = dateATransformer + " Format non g�r�";
		if(!StringHelper.isNullOrEmptyOrSpace(dateATransformer)){
			//Pour boucler sur les diff�rents formats, il nous faut un booleen et un compteur
			boolean transformationReussie = false;
			int tabFormatLen = 0;
			//date ou l'on stockera la dateATransformer au format date
			Date dateInitial = null;

			//tant que l'on a pas r�ussie � trouver le bon format et qu'il en reste � tester, on boucle
			while (!transformationReussie && tabFormatLen < FORMAT_DATE.length){
				//par d�faut on condid�re que l'on a r�ussie la transformation

				try{
					if(isDate(dateATransformer, FORMAT_DATE[tabFormatLen])){
						dateInitial = parse(FORMAT_DATE[tabFormatLen], dateATransformer);
						transformationReussie=true;
					}
					else{
						tabFormatLen++;
					}
				}catch(ParseException e){
					//en cas d'�chec de parsage, on met le booleen � false et on boucle s'il reste des valeurs � tester
					transformationReussie=false;
					tabFormatLen++;
				}
			}
			//si la transformation a bien r�ussie, on format la date en String sur le format par d�faut
			if(transformationReussie){
				if(FORMAT_DATE[tabFormatLen].length()>15){
					resultat = formatDateComplete(dateInitial);
				}
				else{
					resultat = formatDateCourt(dateInitial);
				}
			}
		}
		return resultat;
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type Date
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @return Valeur convertie
	 */
	public static Date tryParse(final Object value) {

		return tryParseFormat(DEFAULT_PATTERN, value);
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type Date
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @param defaultValue
	 *            Valeur par d�faut
	 * @return Valeur convertie ; Sinon valeur par d�faut
	 */
	public static Date tryParse(final Object value, final Date defaultValue) {

		return tryParseFormat(DEFAULT_PATTERN, value, defaultValue);
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type Date
	 * 
	 * @param format
	 *            Mod�le de date
	 * @param value
	 *            Valeur � convertir
	 * @return Valeur convertie
	 */
	public static Date tryParseFormat(final String format, final Object value) {

		return tryParseFormat(format, value, null);
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type Date
	 * 
	 * @param format
	 *            Mod�le de date
	 * @param value
	 *            Valeur � convertir
	 * @param defaultValue
	 *            Valeur par d�faut
	 * @return Valeur convertie ; Sinon valeur par d�faut
	 */
	public static Date tryParseFormat(final String format, final Object value,
			final Date defaultValue) {

		try {
			final Date result = parse(format, value);
			return result == null ? defaultValue : result;
		} catch (ParseException ex) {
			return defaultValue;
		}
	}

	/**
	 * Initialise une nouvelle instance priv�e de la classe DateHelper
	 */
	private DateHelper() {

	}
}
