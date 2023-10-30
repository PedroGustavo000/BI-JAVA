package felipecorrea.sptech;

public class Main {
    public static void main(String[] args) {
        String a = Insignia.EXPLICACAO_COMPLETA.getDisplayName();
        System.out.println(Insignia.values()[11]);
    }
    public enum Insignia {

        BONS_EXEMPLOS("Bons exemplos"),
        DIVERTIDO("Divertido"),
        DOMINA_ASSUNTO("Domina o assunto"),
        EXPLICACAO_COMPLETA("Explicação completa"),
        PACIENTE("Paciente"),
        RESPOSTAS_OBJETIVAS("Respostas objetivas"),
        EXEMPLOS_RUINS("Exemplos ruins"),
        GROSSEIRO("Grosseiro"),
        NAO_DOMINA_ASSUNTO("Não domina o assunto"),
        RESPOSTAS_RASAS("Respostas rasas"),
        IMPACIENTE("Impaciente"),
        FUGIU_ASSUNTO("Fugiu do assunto");

        @Override
        public String toString() {
            return displayName;
        }


        private final String displayName;

        Insignia(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}