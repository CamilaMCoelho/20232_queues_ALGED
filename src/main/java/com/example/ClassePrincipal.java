package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ClassePrincipal {

    // Declara uma variável privada chamada filas que é um mapa onde as chaves são
    // strings e os valores associados a essas chaves são filas de objetos do tipo
    // Pessoa. Essa estrutura de dados pode ser utilizada para armazenar filas de
    // pessoas.
    private Map<String, Queue<Pessoa>> filas;
    private static Map<String, Set<String>> grupos = new HashMap<>();

    // Construtor da classe que inicializa a variável "filas" como uma instância de
    // HashMap.
    public ClassePrincipal() {
        this.filas = new HashMap<>();
    }

    public void processarComandos() {

        String pathArquivo = "C:\\Users\\user\\Documents\\demo\\src\\main\\java\\com\\example\\filas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(pathArquivo))) {
            
            String linha;

            while ((linha = br.readLine()) != null) {
                linha = linha.trim(); // Remover espaços em branco no início e no final

                if (!linha.isEmpty()) {
                    // System.out.println("Linha lida: " + linha);
                    processarComando(linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processarComando(String comando) {
        String[] partes = comando.split(":");
        String operacao = partes[0].trim();
        String[] parametros = {};

        if (!(operacao.equals("imprime"))) {
            parametros = partes[1].trim().split("\\s+");
        }

        if (partes.length < 1) {
            System.out.println("Comando mal formatado: " + comando);
            return;
        }

        try {
            switch (operacao) {
                case "criaFila":
                    criarFila(parametros[0]);
                    break;
                
                case "atendeFila":
                    atenderFila(parametros);
                    break;

                case "chegou":
                    adicionarNaFila(parametros);
                    break;

                case "desiste":
                    removerDaFila(parametros);
                    break;

                case "grupo":
                    criaGrupo(parametros);
                    break;

                case "conhece":
                    pessoasSeConhecem(parametros);
                    break;

                case "existe":
                    pessoaExiste(parametros);
                    break;

                case "imprime":
                    System.out.println(imprimirFilas());
                    break;

                default:
                    System.out.println("Comando desconhecido: " + operacao);
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar comando: " + comando);
            e.printStackTrace();
        }
    }

    private void criarFila(String parametros) {
        if (!filas.containsKey(parametros)) {
            filas.put(parametros, new LinkedList<>());
        }
    }

    private void atenderFila(String[] idsFilas) {
        for (String idFila : idsFilas) {
            if (filas.containsKey(idFila) && !filas.get(idFila).isEmpty()) {
                filas.get(idFila).poll();
            }
        }
    }

    private void adicionarNaFila(String[] clientes) {
        for (String cliente : clientes) {
            Queue<Pessoa> filaConhecida = encontrarFilaConhecida(cliente);
            if (filaConhecida != null) {
                inserirNaFilaAposConhecido(filaConhecida, cliente);
            } 
            else {
                int menorTamanho = Integer.MAX_VALUE;
                Queue<Pessoa> menorFila = null;
                for (Queue<Pessoa> fila : filas.values()) {
                    if (fila.size() < menorTamanho) {
                        menorTamanho = fila.size();
                        menorFila = fila;
                    }
                }
                if (menorFila != null) {
                    menorFila.add(new Pessoa(cliente));
                } 
            }
            System.out.println(imprimirFilas());
        }
    }

    private Queue<Pessoa> encontrarFilaConhecida(String nome) {
        for (Queue<Pessoa> fila : filas.values()) {
            for (Pessoa pessoa : fila) {
                if (_pessoasSeConhecem(nome, pessoa.getNome())) {
                    return fila;
                }
            }
        }
        return null;
    }

    private void inserirNaFilaAposConhecido(Queue<Pessoa> fila, String nome) {
        int posicaoConhecido1 = -1;
        int posicaoConhecido2 = -1;
        int i = 0;
        for (Pessoa pessoa : fila) {
            if (_pessoasSeConhecem(nome, pessoa.getNome())) {
                if (posicaoConhecido1 == -1) {
                    posicaoConhecido1 = i;
                } else {
                    posicaoConhecido2 = i;
                }
            }
            i++;
        }
        int posicaoMaisDistante = Math.max(posicaoConhecido1, posicaoConhecido2);
        if (posicaoMaisDistante != -1) {
            i = 0;
            Queue<Pessoa> novaFila = new LinkedList<>();
            for (Pessoa pessoa : fila) {
                novaFila.add(pessoa);
                if (i == posicaoMaisDistante) {
                    // Inserir a pessoa imediatamente após o conhecido mais distante
                    novaFila.add(new Pessoa(nome)); 
                }
                i++;
            }
            fila.clear();
            fila.addAll(novaFila);
        }
    }

    private void removerDaFila(String[] clientes) {

        List<String> clientesList = Arrays.asList(clientes);

        for (Queue<Pessoa> fila : filas.values()) {
            fila.removeIf(pessoa -> clientesList.contains(pessoa.getNome()));
        }
    }

    private String imprimirFilas() {
        StringBuilder resultado = new StringBuilder();

        for (Map.Entry<String, Queue<Pessoa>> entry : filas.entrySet()) {
            resultado.append("#").append(entry.getKey()).append(" [");
            Queue<Pessoa> fila = entry.getValue();

            Iterator<Pessoa> iterator = fila.iterator();
            while (iterator.hasNext()) {
                Pessoa pessoa = iterator.next();
                resultado.append(pessoa.getNome());

                // Adiciona vírgula apenas se houver mais elementos na fila
                if (iterator.hasNext()) {
                    resultado.append(", ");
                }
            }

            resultado.append("]\n");
        }

        return resultado.toString();
    }

    private void criaGrupo(String[] parametros) {
        String nomeGrupo = parametros[0].replace(":", "");
        Set<String> pessoas = new HashSet<>();
        for (int i = 0; i < parametros.length; i++) {
            pessoas.add(parametros[i]);
        }
        grupos.put(nomeGrupo, pessoas);
    }

    private void pessoasSeConhecem(String[] parametros) {
        String nome1 = parametros[0];
        String nome2 = parametros[1];
        if (_pessoasSeConhecem(nome1, nome2)) {
            System.out.println("[" + nome1 + "] conhece [" + nome2 + "]");
        } else {
            System.out.println("[" + nome1 + "] NÃO conhece [" + nome2 + "]");
        }
    }
    private static boolean _pessoasSeConhecem(String nome1, String nome2) {
        // Faz uma busca sequencial para verificar se as pessoas se conhecem ou não
        for (Set<String> pessoas : grupos.values()) {
            if (pessoas.contains(nome1) && pessoas.contains(nome2)) {
                return true;
            }
        }
        return false;
    }

    private void pessoaExiste(String[] parametros) {
        String nomePessoa = parametros[0];
        if (_pessoaExiste(nomePessoa)) {
            System.out.println("[" + nomePessoa + "] existe!");
        } else {
            System.out.println("[" + nomePessoa + "] NÃO existe!");
        }
    }

    private static boolean _pessoaExiste(String nome) {
        for (Set<String> pessoas : grupos.values()) {
            if (pessoas.contains(nome)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ClassePrincipal filaBrasileira = new ClassePrincipal();
        filaBrasileira.processarComandos();
    }
}