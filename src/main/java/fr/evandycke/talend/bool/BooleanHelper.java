package fr.evandycke.talend.bool;

import java.util.Locale;

import fr.evandycke.talend.string.StringHelper;

/**
 * BooleanHelper est la classe des outils de manipulation des objets de type
 * Boolean.
 * 
 * @author elie.vandycke
 * 
 */
public class BooleanHelper {

	/**
	 * Bool�en par d�faut
	 */
	private static final Boolean DEFAULT_BOOLEAN = false;

	/**
	 * Table de correspondance
	 */
	private static final BooleanMapping BOOLEAN_MAPPING = new BooleanMapping();

	/**
	 * Convertit l'objet sp�cifi� en objet de type Boolean
	 * 
	 * @param value
	 *            Valeur �convertir
	 * @return Valeur convertie
	 */
	private static Boolean parse(final Object value) {

		if (value == null) {
			return null;
		}

		final String strValue = StringHelper.tryParse(value).toUpperCase(Locale.FRANCE);		
		return BOOLEAN_MAPPING.containsKey(strValue) ? BOOLEAN_MAPPING.get(strValue) : null;
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type Boolean
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @return Valeur convertie
	 */
	public static Boolean tryParse(final Object value) {

		return tryParse(value, DEFAULT_BOOLEAN);
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type Boolean
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @param defaultValue
	 *            Valeur par d�faut
	 * @return Valeur convertie ; Sinon valeur par d�faut
	 */
	public static Boolean tryParse(final Object value, final Boolean defaultValue) {

		final Boolean result = parse(value);
		return result == null ? defaultValue : result;
	}

	/**
	 * Initialise une nouvelle instance priv�e de la classe BooleanHelper
	 */
	private BooleanHelper() {

	}
}