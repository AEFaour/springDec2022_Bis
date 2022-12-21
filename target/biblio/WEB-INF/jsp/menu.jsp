<div class="container">
    <div class="row">
		<div class="col-md-12">
			<h3>Fonctionnalit�s</h3>
			<div class="tabbable-panel">
				<div class="tabbable-line">
					<ul class="nav nav-tabs ">
						<li  >
							<a href="<%=application.getContextPath()%>/spring/jsp/home" data-toggle="tab" >Accueil</a>
						</li>
						<li>
							<a href="<%=application.getContextPath()%>/spring/jsp/livres/lister" data-toggle="tab" >Gestion des Livres</a>
						</li>
						<li>
							<a href="<%=application.getContextPath()%>/spring/jsp/emprunts/lister" data-toggle="tab">Gestion des Emprunts</a>
						</li>
						<li>
							<a href="<%=application.getContextPath()%>/spring/jsp/adherents/lister" data-toggle="tab">Gestion des Adh�rents</a>
						</li>
						<li>
							<a href="<%=application.getContextPath()%>/logout" data-toggle="tab">D�connexions</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>