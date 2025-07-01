string mensagemDeBoasVindas = "Boas vindas ao Screen Sound";
List<string> listaDasBandas = new List<string> { "U2", "The Beatles", "Calypso" };

Dictionary<string, List<int>> bandasComAvaliacoes = new Dictionary<string, List<int>>();
bandasComAvaliacoes.Add("U2", new List<int> { 10, 9, 8 });
bandasComAvaliacoes.Add("The Beatles", new List<int> ());

void ExibirLogo()
{
    Console.WriteLine(
        @"

░██████╗░█████╗░██████╗░███████╗███████╗███╗░░██╗  ░██████╗░█████╗░██╗░░░██╗███╗░░██╗██████╗░
██╔════╝██╔══██╗██╔══██╗██╔════╝██╔════╝████╗░██║  ██╔════╝██╔══██╗██║░░░██║████╗░██║██╔══██╗
╚█████╗░██║░░╚═╝██████╔╝█████╗░░█████╗░░██╔██╗██║  ╚█████╗░██║░░██║██║░░░██║██╔██╗██║██║░░██║
░╚═══██╗██║░░██╗██╔══██╗██╔══╝░░██╔══╝░░██║╚████║  ░╚═══██╗██║░░██║██║░░░██║██║╚████║██║░░██║
██████╔╝╚█████╔╝██║░░██║███████╗███████╗██║░╚███║  ██████╔╝╚█████╔╝╚██████╔╝██║░╚███║██████╔╝
╚═════╝░░╚════╝░╚═╝░░╚═╝╚══════╝╚══════╝╚═╝░░╚══╝  ╚═════╝░░╚════╝░░╚═════╝░╚═╝░░╚══╝╚═════╝░
"
    );
    Console.WriteLine(mensagemDeBoasVindas);
}

void ExibirOpcoesDoMenu()
{
    ExibirLogo();
    Console.WriteLine("\nDigite 1 para registrar uma banda");
    Console.WriteLine("Digite 2 para mostrar todas as bandas");
    Console.WriteLine("Digite 3 para avaliar uma banda");
    Console.WriteLine("Digite 4 para exibir a média de uma banda");
    Console.WriteLine("Digite -1 para sair");
    Console.Write("\nDigite a sua opção: ");

    string opcaoEscolhida = Console.ReadLine()!;
    int opcaoEscolhidaNumerica = int.Parse(opcaoEscolhida);

    switch (opcaoEscolhidaNumerica)
    {
        case 1:
            RegistrarBanda();
            break;
        case 2:
            MostrarBandasRegistradas();
            break;
        case 3:
            AvaliarUmaBanda();
            break;
        case 4:
            ExibirMediaDasAvaliacoes();
            break;
        case -1:
            Console.WriteLine("Tchau tchau :)");
            break;
        default:
            Console.WriteLine("Opção inválida");
            break;
    }
}

void RegistrarBanda()
{
    Console.Clear();
    Console.WriteLine("**********************");
    Console.WriteLine("Registro de bandas");
    Console.WriteLine("**********************\n");
    Console.Write("Digite o nome da banda que deseja registrar: ");
    string nomeDaBanda = Console.ReadLine()!;
    bandasComAvaliacoes.Add(nomeDaBanda, new List<int>());
    Console.WriteLine($"A banda {nomeDaBanda} foi registrada com sucesso!");
    Thread.Sleep(2000);
    Console.Clear();
    ExibirOpcoesDoMenu();
}

void MostrarBandasRegistradas()
{
    Console.Clear();
    Console.WriteLine("************************************");
    Console.WriteLine("Exibindo todas as bandas registradas");
    Console.WriteLine("************************************\n");

    //for (int i = 0; i < listaDasBandas.Count; i++)
    //{
    //Console.WriteLine($"Banda: {listaDasBandas[i]}");
    //}

    foreach (string banda in bandasComAvaliacoes.Keys)
    {
        Console.WriteLine($"Banda: {banda}");
    }

    Console.WriteLine("\nDigite uma tecla para voltar ao menu principal");
    Console.ReadKey();
    Console.Clear();
    ExibirOpcoesDoMenu();
}

void AvaliarUmaBanda()
{
    Console.Clear();
    Console.WriteLine("**********************");
    Console.WriteLine("Avaliação de bandas");
    Console.WriteLine("**********************\n");
    Console.Write("Digite o nome da banda que deseja avaliar: ");
    string nomeDaBanda = Console.ReadLine()!;

    if (bandasComAvaliacoes.ContainsKey(nomeDaBanda))
    {
        Console.Write("Digite a nota de 0 a 10: ");
        int nota = int.Parse(Console.ReadLine()!);
        bandasComAvaliacoes[nomeDaBanda].Add(nota);
        Console.WriteLine($"A banda {nomeDaBanda} foi avaliada com sucesso!");
    }
    else
    {
        Console.WriteLine($"\nA banda {nomeDaBanda} não está registrada.");
        Console.WriteLine("Digite uma tecla para voltar ao menu principal");
        Console.ReadKey();
        Console.Clear();
        ExibirOpcoesDoMenu();
    }

    Thread.Sleep(2000);
    Console.Clear();
}

void ExibirMediaDasAvaliacoes() {
    Console.Clear();
    Console.WriteLine("************************************");
    Console.WriteLine("Exibindo a média das avaliações");
    Console.WriteLine("************************************\n");
    Console.Write("Digite o nome da banda: ");
    string nomeDaBanda = Console.ReadLine()!;

    if (bandasComAvaliacoes.ContainsKey(nomeDaBanda))
    {
        List<int> avaliacoes = bandasComAvaliacoes[nomeDaBanda];
        if (avaliacoes.Count > 0)
        {
            double media = avaliacoes.Average();
            Console.WriteLine($"A média das avaliações da banda {nomeDaBanda} é: {media}");
        }
        else
        {
            Console.WriteLine($"A banda {nomeDaBanda} ainda não possui avaliações.");
        }
    }
    else
    {
        Console.WriteLine($"\nA banda {nomeDaBanda} não está registrada.");
    }

    Console.WriteLine("\nDigite uma tecla para voltar ao menu principal");
    Console.ReadKey();
    Console.Clear();
}

ExibirOpcoesDoMenu();
