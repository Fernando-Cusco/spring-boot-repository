package ec.edu.ups.springbootdatajpa.utils.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
    
    private String url;
    private Page<T> page;
    private int totalPaginas;
    private int numeroElementosPagina;
    private int paginaActual;
    private List<PageItem> paginas;
    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.paginas =  new ArrayList<PageItem>();
        this.numeroElementosPagina = page.getSize();
        totalPaginas = page.getTotalPages();
        paginaActual = page.getNumber() + 1;
        int desde, hasta;
        if (totalPaginas <= numeroElementosPagina) {
            desde = 1;
            hasta = totalPaginas;
        } else {
            if (paginaActual <= numeroElementosPagina /2) {
                desde = 1;
                hasta = numeroElementosPagina;
            } else if (paginaActual >= totalPaginas - numeroElementosPagina /2 ) {
                desde = totalPaginas - numeroElementosPagina + 1;
                hasta = numeroElementosPagina;
            } else {
                desde = paginaActual - numeroElementosPagina /2;
                hasta = numeroElementosPagina;
            }
        }
        for (int i = 0; i < hasta; i++) {
            paginas.add(new PageItem(desde + i, paginaActual == desde + i));
        }
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Page<T> getPage() {
        return page;
    }
    public void setPage(Page<T> page) {
        this.page = page;
    }
    public int getTotalPaginas() {
        return totalPaginas;
    }
    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }
    public int getNumeroElementosPagina() {
        return numeroElementosPagina;
    }
    public void setNumeroElementosPagina(int numeroElementosPagina) {
        this.numeroElementosPagina = numeroElementosPagina;
    }
    public int getPaginaActual() {
        return paginaActual;
    }
    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }
    public List<PageItem> getPaginas() {
        return paginas;
    }
    public void setPaginas(List<PageItem> paginas) {
        this.paginas = paginas;
    }
    public boolean isFirts() {
        return page.isFirst();
    }
    public boolean isLast() {
        return page.isLast();
    }
    public boolean isHasNext() {
        return page.hasNext();
    }
    public boolean isHasPrevious() {
        return page.hasPrevious();
    }
}
