const client = algoliasearch("EUXHYU6U4I", "dc139b675f3962efc57140dc643e3475");
const product = client.initIndex('products');
const teams = client.initIndex('teams');

autocomplete('#aa-search-input', {}, 
    {
      source: autocomplete.sources.hits(product, { hitsPerPage: 5 }),
      displayKey: 'name',
      templates: {
        header: '<div class="aa-suggestions-category">Products</div>',
        suggestion({_highlightResult}) {
        	return `<a href="/product/${_highlightResult.id.value}"> <span>` +
        	`${_highlightResult.name.value}</span> </a> `;
          
        }
      }
    }
);
//return `<a href="/product/${_highlightResult.name.value}"> <span>` +
//`${_highlightResult.name.value}</span> </a> `;