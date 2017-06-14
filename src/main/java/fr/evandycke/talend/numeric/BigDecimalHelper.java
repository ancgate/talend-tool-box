package fr.evandycke.talend.numeric;

import java.math.BigDecimal;
import java.math.RoundingMode;

import fr.evandycke.talend.string.StringHelper;

/**
 * BigDecimalHelper est la classe des outils de manipulation des objets de type
 * BigDecimal.
 * 
 * @author elie.vandycke
 * 
 */
public final class BigDecimalHelper {

	/**
	 * D�termine si la valeur sp�cifi�e est �gale � z�ro (quelque soit la
	 * pr�cision)
	 * 
	 * @param value
	 *            Valeur � tester
	 * @return Vrai si la valeur est �gale � z�ro ; Sinon Faux
	 */
	public static boolean equalsZero(final Object value) {

		final BigDecimal bdValue = tryParseToBigDecimal(value, null);
		return bdValue == null ? false
				: BigDecimal.ZERO.compareTo(bdValue) == 0;
	}

	/**
	 * D�termine si la valeur sp�cifi�e est diff�rente de z�ro (quelque soit la
	 * pr�cision)
	 * 
	 * @param value
	 *            Valeur � tester
	 * @return Vrai si la valeur est diff�rente de z�ro ; Sinon Faux
	 */
	public static boolean notEqualsZero(final Object value) {

		return !equalsZero(value);
	}

	/**
	 * Convertit un objet en BigDecimal
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @return R�sultat de la conversion
	 */
	public static BigDecimal parseToBigDecimal(final Object value) {

		return parseToBigDecimal(value, null);
	}

	/**
	 * Convertit un objet en BigDecimal
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @param nbrDecimal
	 *            Nombre de d�cimales
	 * @return R�sultat de la conversion
	 */
	public static BigDecimal parseToBigDecimal(final Object value,
			final Integer nbrDecimal) {

		if (value == null) {
			return null;
		}

		BigDecimal returnValue;

		if (value instanceof String) {
			final String strVal = ((String) value).trim();
			if ("".equals(strVal)) {
				return null;
			} else {
				// Remplacement de "," par "." sinon on obtiendra z�ro
				String s = strVal.replace(',', '.');

				// Remplacement de " " par "" sinon on obtiendra z�ro
				s = StringHelper.removeAllWhitespaceCharacters(s);
				// on v�rifie qu'il s'agisse bien d'une valeur num�rique
				if (StringHelper.isNumeric(s)) {
					returnValue = new BigDecimal(s);
				} else {
					return null;
				}
			}
		} else if (value instanceof Integer) {
			returnValue = new BigDecimal((Integer) value);
		} else if (value instanceof Double) {
			returnValue = BigDecimal.valueOf((Double) value);
		} else if (value instanceof Long) {
			returnValue = new BigDecimal((Long) value);
		} else if (value instanceof BigDecimal) {
			returnValue = (BigDecimal) value;
		} else if (value instanceof Float) {
			returnValue = new BigDecimal(String.valueOf(value));
		} else {
			returnValue = BigDecimal.ZERO;
		}

		return nbrDecimal == null ? returnValue
				: round(returnValue, nbrDecimal);
	}

	/**
	 * Arrondit le BigDecimal au nombre de d�cimales indiqu�
	 * 
	 * @param value
	 *            Valeur � arrondir
	 * @param nbDecimal
	 *            Nombre de d�cimales
	 * @return R�sultat de l'arrondi
	 */
	public static BigDecimal round(final BigDecimal value, final int nbDecimal) {

		if (value == null) {
			return null;
		}

		return value.setScale(nbDecimal, RoundingMode.HALF_UP);
	}

	/**
	 * Convertit un objet en BigDecimal
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @return R�sultat de la conversion
	 */
	public static BigDecimal tryParseToBigDecimal(final Object value) {

		return tryParseToBigDecimal(value, BigDecimal.ZERO);
	}

	/**
	 * Convertit un objet en BigDecimal et renvoit la valeur par d�faut en cas
	 * de probl�me de conversion
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @param defaultValue
	 *            Valeur par d�faut
	 * @return R�sultat de la conversion
	 */
	public static BigDecimal tryParseToBigDecimal(final Object value,
			final BigDecimal defaultValue) {

		BigDecimal retour = parseToBigDecimal(value);
		if (retour == null) {
			retour = defaultValue;
		}
		return retour;
	}

	/**
	 * Initialise une nouvelle instance priv�e de la classe BigDecimalHelper
	 */
	private BigDecimalHelper() {

	}
}
