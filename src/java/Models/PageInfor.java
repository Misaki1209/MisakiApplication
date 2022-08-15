package Models;

/**
 *
 * @author Misaki
 */
public class PageInfor {
    private int cp, nrpp, size, np, begin, end;
    private int[] arrNrpp={3,6,9,12,15,18,21,24,27,30};
    private int pageStart, pageEnd;
    
    public PageInfor(){
    }

    public PageInfor(int cp, int nrpp, int size, int np, int begin, int end) {
        this.cp = cp;
        this.nrpp = nrpp;
        this.size = size;
        this.np = np;
        this.begin = begin;
        this.end = end;
    }
    
    public PageInfor(int cp, int nrpp, int size){
        this.cp = cp;
        this.nrpp = nrpp;
        this.size = size;
        
        np = (size + arrNrpp[nrpp] - 1)/arrNrpp[nrpp];
        
        this.cp = this.cp < 0 ? 0 : this.cp;
        this.cp = this.cp > np-1 ? np-1 : this.cp;
        
        begin = arrNrpp[nrpp] * cp;
        
        end = begin + arrNrpp[nrpp] - 1;
        end = end > this.size-1 ? this.size-1:end;
        
        pageStart = cp - 2;
        pageStart = pageStart < 0 ? 0 : pageStart;
        
        pageEnd = cp + 2;
        pageEnd = pageEnd > np-1 ? np-1 : pageEnd;
        
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getNrpp() {
        return nrpp;
    }

    public void setNrpp(int nrpp) {
        this.nrpp = nrpp;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNp() {
        return np;
    }

    public void setNp(int np) {
        this.np = np;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int[] getArrNrpp() {
        return arrNrpp;
    }

    public void setArrNrpp(int[] arrNrpp) {
        this.arrNrpp = arrNrpp;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
    
}
