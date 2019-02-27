<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Todos os turnos - VT</title>
</head>
<body>
	<jsp:include page="/adds/navbar.jsp"></jsp:include>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Período</th>
				<th scope="col">Vendedor</th>
				<th scope="col">Data</th>
				<th scope="col">Vales</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="t" items="${sessionScope.turnos}" varStatus="posicao">
				<tr>
					<td scope="row"><a href="verturno">${posicao.index+1}</a></td>
					<td>${t.turno}</td>
					<td>${t.idVendedor}</td>
					<td>${t.data}</td>
					<td>${t.quantVales}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<jsp:include page="/adds/rodape.jsp"></jsp:include>
</body>
</html>