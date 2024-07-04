const modules=import.meta.glob("@/components/common/modal/*.vue",{eager:true}) as Record<string, { default:any }>;

const modalComponents:{[key:string]:any}={};

for(const path in modules){
    const  moduleName=path.split('/').pop()?.replace('.vue','');
    if(moduleName){
        modalComponents[moduleName]=modules[path].default;
    }
}

export default modalComponents;