package fr.evandycke.talend.numeric;

import java.math.BigDecimal;

import fr.evandycke.talend.string.StringHelper;

/**
 * FloatHelper est la classe des outils de manipulation des objets de type
 * Float.
 * 
 * @author elie.vandycke
 * 
 */
public final class FloatHelper {

	/**
	 * Entier par d�faut
	 */
	private static final Float DEFAULT_FLOAT = 0f;

	/**
	 * D�termine si l'objet sp�cifi� est nul ou vaut z�ro
	 * 
	 * @param value
	 *            Valeur � tester
	 * @return Vrai si l'objet sp�cifi� est nul ou vaut z�ro ; Sinon Faux
	 */
	public static boolean isNullOrZero(final Object value) {

		final Float floatValue = tryParse(value);
		return floatValue == null || DEFAULT_FLOAT.equals(floatValue);
	}

	/**
	 * Convertit l'objet sp�cifi� en objet de type Float
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @return Valeur convertie
	 */
	private static Float parse(final Object value) {

		if (value == null) {
			return null;
		}

		Float returnValue;

		if (value instanceof String) {
			final String strVal = ((String) value).trim();
			if ("".equals(strVal)) {
				return null;
			} else {
				// Remplacement de "," par "." sinon, on obtiendra z�ro
				String s = strVal.replace(',', '.');

				// Remplacement de " " par "" sinon on obtiendra z�ro
				s = StringHelper.removeAllWhitespaceCharacters(s);
				returnValue = Float.valueOf(s);
			}
		} else if (value instanceof Integer) {
			returnValue = ((Integer) value).floatValue();
		} else if (value instanceof Double) {
			returnValue = ((Double) value).floatValue();
		} else if (value instanceof BigDecimal) {
			returnValue = ((BigDecimal) value).floatValue();
		} else if (value instanceof Long) {
			returnValue = ((Long) value).floatValue();
		} else if (value instanceof Float) {
			returnValue = (Float) value;
		} else {
			returnValue = Float.valueOf(0);
		}

		return returnValue;
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type Float
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @return Valeur convertie
	 */
	public static Float tryParse(final Object value) {

		return tryParse(value, DEFAULT_FLOAT);
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type Float
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @param defaultValue
	 *            Valeur par d�faut
	 * @return Valeur convertie ; Sinon valeur par d�faut
	 */
	public static Float tryParse(final Object value, final Float defaultValue) {

		Float result = defaultValue;

		try {
			result = parse(value);
		} catch (NumberFormatException e) {
			result = defaultValue;
		}

		return result == null ? defaultValue : result;
	}

	/**
	 * Initialise une nouvelle instance priv�e de la classe FloatHelper
	 */
	private FloatHelper() {

	}
}
