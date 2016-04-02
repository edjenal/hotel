package util;

public class ValidaCpf {

	public static String cpfValido(String cpf){
		String temp = cpf!= null ? cpf.replaceAll("\\D+", "") : null;
		
		if (temp == null || temp.equals("")) {
			return "CPF inválido!";
		} else if (cpf!=null && cpf!="" && validaCPF(cpf)){
			return null;
		} else {
			return "CPF inválido!";
		}
	}
	
	private static boolean validaCPF(String cpf) {

		if (cpf.equals("999.999.999-99") || cpf.equals("888.888.888-88") || cpf.equals("777.777.777-77")
				|| cpf.equals("666.666.666-66") || cpf.equals("555.555.555-55") || cpf.equals("444.444.444-44")
				|| cpf.equals("333.333.333-33") || cpf.equals("222.222.222-22") || cpf.equals("111.111.111-11")
				|| cpf.equals("000.000.000-00") || cpf.length() != 14) {
			return false;
		} else {
			cpf = cpf.replaceAll("\\.", "");
			String partesCPF[] = cpf.split("-");
			int identificacao = Integer.parseInt(partesCPF[0]);
			int primDigVerif = Integer.parseInt(partesCPF[1].substring(0, 1));
			int segDigVerif = Integer.parseInt(partesCPF[1].substring(1, 2));
			long primeiroDigito = modulo((long) identificacao);
			long segundoDigito = modulo((long) identificacao * 10 + primeiroDigito);
			return primDigVerif == primeiroDigito && segDigVerif == segundoDigito;
		}
	}

	private static long modulo(long numero) {
		long soma = 0;
		long multiplicador = 2;
		while (numero > 0) {
			long digito = numero % 10;
			soma += multiplicador * digito;
			numero /= 10;
			multiplicador++;
		}
		long resto = soma % 11;
		if (resto < 2)
			return 0;
		else
			return 11 - resto;
	}
}
