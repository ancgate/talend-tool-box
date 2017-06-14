package fr.evandycke.talend.string;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.evandycke.talend.numeric.IntegerHelper;

/**
 * StringHelper est la classe des outils de manipulation des objets de type
 * String.
 * 
 * @author elie.vandycke
 */
public final class StringHelper {

	/**
	 * Chaine vide
	 */
	private static final String CHAINE_VIDE = "";

	/**
	 * Norme UNICODE par d�faut
	 */
	private static final Form DEFAULT_FORM = Form.NFD;

	/**
	 * Ajoute les z�ros d'ent�te � la valeur sp�cifi�e
	 * 
	 * @param size
	 *            Taille de la chaine retourn�e
	 * @param value
	 *            Valeur � convertir
	 * @return Valeur convertie
	 */
	public static String addLeadingZero(final int size, final Object value) {

		final int intValue = IntegerHelper.tryParse(value);
		return String.format(getLeadingZeroFormat(size), intValue);
	}

	/**
	 * Obtient le format des z�ros d'ent�te en fonction de la taille sp�cifi�e
	 * 
	 * @param size
	 *            Taille de la chaine
	 * @return Format
	 */
	private static String getLeadingZeroFormat(final int size) {

		final StringBuilder strBld = new StringBuilder(3);
		return strBld.append("%0").append(size).append("d").toString();
	}

	/**
	 * D�termine si la chaine sp�cifi�e est nulle, vide, ou pleine d'espaces
	 * 
	 * @param value
	 *            Valeur � tester
	 * @return Vrai si la valeur est nulle, vide ou pleine d'espaces ; Sinon
	 *         Faux
	 */
	public static boolean isNullOrEmptyOrSpace(final String value) {

		return value == null || CHAINE_VIDE.equalsIgnoreCase(value.trim());
	}

	/**
	 * Retourne Vrai si la cha�ne de caract�res est num�rique
	 * 
	 * @param value
	 *            Cha�ne de caract�res
	 * @return Vrai si la cha�ne de caract�res est num�rique ; Sinon Faux
	 */
	public static boolean isNumeric(final String value) {

		if (isNullOrEmptyOrSpace(value)) {
			return false;
		}

		final Pattern motif = Pattern.compile("((-|\\+)?[0-9]+(\\.[0-9]+)?)+");
		final Matcher correspondance = motif.matcher(value);
		return correspondance.matches();
	}

	/**
	 * Normalise la valeur sp�cifi�e
	 * 
	 * @param value
	 *            Valeur � normaliser
	 * @return Valeur normalis�e
	 */
	public static String normalize(final Object value) {

		return normalize(value, DEFAULT_FORM);
	}

	/**
	 * Normalise la valeur sp�cifi�e en fonction de la norme indiqu�e
	 * 
	 * @param value
	 *            Valeur � normaliser
	 * @param form
	 *            Norme � utiliser
	 * @return Valeur normalis�e
	 */
	public static String normalize(final Object value, Form form) {

		String strValue = tryParse(value);
		return Normalizer.normalize(strValue, form == null ? DEFAULT_FORM
				: form);
	}

	/**
	 * Retourne une valeur al�atoire, comprise dans la liste des valeurs
	 * sp�cifi�es
	 * 
	 * @param values
	 *            Valeurs
	 * @return Valeur al�atoire
	 */
	public static String random(final String... values) {

		String result = CHAINE_VIDE;

		if (values != null) {

			final List<String> valueList = new ArrayList<String>();
			for (final String s : values) {
				valueList.add(s);
			}

			final Random randomGenerator = new Random();
			final int index = randomGenerator.nextInt(valueList.size());
			result = valueList.get(index);
		}

		return result;
	}

	/**
	 * Retire tous les espaces de la chaine sp�cifi�e
	 * 
	 * @param value
	 *            Valeur � nettoyer
	 * @return Valeur nettoy�e
	 */
	public static String removeAllWhitespaceCharacters(final String value) {

		// Si la valeur est nulle, on retourne null sinon on retourne le
		// r�sultat du nettoyage
		return null == value ? value : value.replaceAll("\\s", "");
	}

	/**
	 * R�p�te une chaine de caract�res autant de fois que voulu et retourne le
	 * r�sultat
	 * 
	 * @param value
	 *            Chaine de caract�res � r�p�ter
	 * @param nbRepetition
	 *            Nombre de r�p�tition
	 * @return R�sultat
	 */
	public static String repeat(final String value, final int nbRepetition) {

		if (isNullOrEmptyOrSpace(value)) {
			return CHAINE_VIDE;
		}

		final StringBuilder builder = new StringBuilder();

		for (int i = 0; i < nbRepetition; i++) {
			builder.append(value);
		}

		return builder.toString();
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type String
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @return Valeur convertie
	 */
	public static String tryParse(final Object value) {

		return tryParse(value, CHAINE_VIDE);
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type String
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @param defaultValue
	 *            Valeur par d�faut
	 * @return Valeur convertie ; Sinon valeur par d�faut
	 */
	public static String tryParse(final Object value, final String defaultValue) {

		final String strResult = String.valueOf(value);
		return "null".equalsIgnoreCase(strResult) ? defaultValue : strResult;
	}

	/**
	 * Initialise une nouvelle instance priv�e de la classe StringHelper
	 */
	private StringHelper() {

	}
}