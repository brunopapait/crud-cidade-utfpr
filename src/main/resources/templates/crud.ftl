<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>CRUD Cidades</title>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>
<div class="container-fluid">
  <div class="jumbotron mt-5">
    <h1>GERENCIAMENTO DE CIDADES</h1>
    <p>UM CRUD PARA CRIAR, ALTERAR, EXCLUIR E LISTAR CIDADES</p>
  </div>
    <#if cidadeAtual??>
  <form action="/alterar" method="POST" class="needs-validation" novalidate>
    <input type="hidden" name="nomeCidadeAtual" value="${(cidadeAtual.nomeCidade)!}"/>
    <input type="hidden" name="nomeEstadoAtual" value="${(cidadeAtual.nomeEstado)!}"/>
      <#else>
    <form action="/criar" method="POST" class="needs-validation" novalidate>
        </#if>

      <div class="form-group">
        <label for="nome">Cidade:</label>
        <input
                value="${(cidadeAtual.nomeCidade)!}${nomeCidadeInformado!}"
                name="nomeCidade" type="text"
                class="form-control ${(nomeCidade??)?then('is-invalid', '')}"
                placeholder="Informe o nome da cidade"
                id="nomeCidade">

        <div class="invalid-feedback">
            ${nomeCidade!}
        </div>
      </div>
      <div class="form-group">
        <label for="estado">Estado:</label>
        <input
                value="${(cidadeAtual.nomeEstado)!}${nomeEstadoInformado!}"
                name="nomeEstado"
                type="text"
                class="form-control ${(nomeEstado??)?then('is-invalid', '')}"
                placeholder="Informe o estado ao qual a cidade pertence"
                id="nomeEstado">

        <div class="invalid-feedback">
            ${nomeEstado!}
        </div>
      </div>
        <#if cidadeAtual??>
          <button type="submit" class="btn btn-warning">CONCLUIR ALTERAÇÃO</button>
        <#else>
          <button type="submit" class="btn btn-primary">CRIAR</button>
        </#if>
    </form>
    <table class="table table-striped table-hover mt-5">
      <thead class="thead-dark">
      <tr>
        <th>Nome</th>
        <th>Estado</th>
        <th>Ações</th>
      </tr>
      </thead>
      <tbody>
      <#list listaCidades as cidade>
        <tr>
          <td>${cidade.nomeCidade}</td>
          <td>${cidade.nomeEstado}</td>
          <td>
            <div class="d-flex d-justify-content-center">
              <a href="/preparaAlterar?nome=${cidade.nomeCidade}&estado=${cidade.nomeEstado}"
                 class="btn btn-warning mr-3">ALTERAR</a>
              <a href="/excluir?nome=${cidade.nomeCidade}&estado=${cidade.nomeEstado}"
                 class="btn btn-danger">EXCLUIR</a>
            </div>
          </td>
        </tr>
      </#list>
      </tbody>
    </table>
</div>
</body>
</html>