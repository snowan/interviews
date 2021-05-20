func findDuplicate(paths []string) [][]string {
    fileDict := make(map[string][]string)
    
    for _, path := range paths {
        idx := strings.IndexByte(path, ' ')
        
        p_dir, files := path[:idx], path[idx + 1:]
        for _, file := range strings.Split(files, " ") {
            fIdx := strings.IndexByte(file, '(')
            fileName, content := file[:fIdx], file[fIdx + 1:]
            fileDict[content] = append(fileDict[content], p_dir + "/" + fileName)
        }
    }
    
    res := [][]string{} 
    for _, group := range fileDict {
        if len(group) > 1 {
            res = append(res, group)
        }   
    }
    return res
}
