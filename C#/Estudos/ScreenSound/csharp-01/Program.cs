using System;
using System.Collections.Generic;
using System.Linq;

class Program
{
    class Banda
    {
        public string Nome { get; set; }
        public string GeneroMusical { get; set; }
        public int AnoFormacao { get; set; }
        public List<int> Avaliacoes { get; set; }

        public Banda(string nome, string generoMusical, int anoFormacao)
        {
            Nome = nome;
            GeneroMusical = generoMusical;
            AnoFormacao = anoFormacao;
            Avaliacoes = new List<int>();
        }
    }

    static List<Banda> bandas = new List<Banda>();

    static void Main()
    {
        while (true)
        {
            ExibirMensagemDeBoasVindas();
        }
    }

    static void ExibirMensagemDeBoasVindas()
    {
        Console.OutputEncoding = System.Text.Encoding.UTF8;

        Console.WriteLine(
            @"╔═══╗╔═══╗╔═══╗╔═══╗╔═══╗╔═╗─╔╗     ╔═══╗╔═══╗╔╗─╔╗╔═╗─╔╗╔═══╗
║╔═╗║║╔═╗║║╔═╗║║╔══╝║╔══╝║║╚╗║║     ║╔═╗║║╔═╗║║║─║║║║╚╗║║╚╗╔╗║
║╚══╗║║─╚╝║╚═╝║║╚══╗║╚══╗║╔╗╚╝║     ║╚══╗║║─║║║║─║║║╔╗╚╝║─║║║║
╚══╗║║║─╔╗║╔╗╔╝║╔══╝║╔══╝║║╚╗║║     ╚══╗║║║─║║║║─║║║║╚╗║║─║║║║
║╚═╝║║╚═╝║║║║╚╗║╚══╗║╚══╗║║─║║║     ║╚═╝║║╚═╝║║╚═╝║║║─║║║╔╝╚╝║
╚═══╝╚═══╝╚╝╚═╝╚═══╝╚═══╝╚╝─╚═╝     ╚═══╝╚═══╝╚═══╝╚╝─╚═╝╚═══╝"
        );

        Console.WriteLine("\nBem-vindo ao Screen Sound!");
        Console.WriteLine("Digite 1 para registrar uma banda");
        Console.WriteLine("Digite 2 para mostrar todas as bandas registradas");
        Console.WriteLine("Digite 3 para avaliar uma banda");
        Console.WriteLine("Digite 4 para exibir a média de avaliações de uma banda");
        Console.WriteLine("Digite -1 para sair do programa");

        Console.Write("Digite a sua opção: ");
        string opcao = Console.ReadLine()!;

        if (!int.TryParse(opcao, out int opcaoInt))
        {
            Console.WriteLine("Por favor, digite um número válido.");
            PausarELimpar();
            return;
        }

        if (opcaoInt == -1)
        {
            Console.WriteLine("Saindo do programa...");
            Environment.Exit(0);
        }
        else if (opcaoInt == 1)
        {
            RegistrarBanda();
        }
        else if (opcaoInt == 2)
        {
            MostrarBandasRegistradas();
        }
        else if (opcaoInt == 3)
        {
            AvaliarBanda();
        }
        else if (opcaoInt == 4)
        {
            ExibirMediaAvaliacoes();
        }
        else
        {
            Console.WriteLine("Opção inválida. Por favor, tente novamente.");
        }

        PausarELimpar();
    }

    static void RegistrarBanda()
    {
        Console.Write("Digite o nome da banda: ");
        string nomeBanda = Console.ReadLine()!;

        Console.Write("Digite o gênero musical da banda: ");
        string generoMusical = Console.ReadLine()!;

        Console.Write("Digite o ano de formação da banda: ");
        string anoStr = Console.ReadLine()!;

        if (!int.TryParse(anoStr, out int anoFormacao))
        {
            Console.WriteLine("Ano inválido.");
            return;
        }

        bandas.Add(new Banda(nomeBanda, generoMusical, anoFormacao));

        Console.WriteLine($"Banda '{nomeBanda}' registrada com sucesso!");
    }

    static void MostrarBandasRegistradas()
    {
        if (bandas.Count == 0)
        {
            Console.WriteLine("Nenhuma banda registrada ainda.");
            return;
        }

        Console.WriteLine("Bandas registradas:");

        foreach (var banda in bandas)
        {
            Console.WriteLine($"- {banda.Nome} ({banda.GeneroMusical}, {banda.AnoFormacao})");
        }
    }

    static void AvaliarBanda()
    {
        if (bandas.Count == 0)
        {
            Console.WriteLine("Nenhuma banda registrada para avaliar.");
            return;
        }

        Console.Write("Digite o nome da banda que deseja avaliar: ");
        string nomeBanda = Console.ReadLine()!;

        var banda = bandas.FirstOrDefault(b =>
            b.Nome.Equals(nomeBanda, StringComparison.OrdinalIgnoreCase)
        );

        if (banda == null)
        {
            Console.WriteLine("Banda não encontrada.");
            return;
        }

        Console.Write("Digite a nota da avaliação (0 a 10): ");
        string notaStr = Console.ReadLine()!;

        if (!int.TryParse(notaStr, out int nota) || nota < 0 || nota > 10)
        {
            Console.WriteLine("Nota inválida.");
            return;
        }

        banda.Avaliacoes.Add(nota);
        Console.WriteLine($"Avaliação adicionada para a banda {banda.Nome}.");
    }

    static void ExibirMediaAvaliacoes()
    {
        if (bandas.Count == 0)
        {
            Console.WriteLine("Nenhuma banda registrada para exibir média.");
            return;
        }

        Console.Write("Digite o nome da banda para ver a média das avaliações: ");
        string nomeBanda = Console.ReadLine()!;

        var banda = bandas.FirstOrDefault(b =>
            b.Nome.Equals(nomeBanda, StringComparison.OrdinalIgnoreCase)
        );

        if (banda == null)
        {
            Console.WriteLine("Banda não encontrada.");
            return;
        }

        if (banda.Avaliacoes.Count == 0)
        {
            Console.WriteLine("Esta banda ainda não possui avaliações.");
            return;
        }

        double media = banda.Avaliacoes.Average();
        Console.WriteLine($"A média das avaliações da banda {banda.Nome} é {media:F2}.");
    }

    static void PausarELimpar()
    {
        Console.WriteLine("\nPressione qualquer tecla para continuar...");
        Console.ReadKey();
        Console.Clear();
    }
}
